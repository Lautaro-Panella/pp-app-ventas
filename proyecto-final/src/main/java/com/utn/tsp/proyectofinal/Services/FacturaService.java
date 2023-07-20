package com.utn.tsp.proyectofinal.Services;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.utn.tsp.proyectofinal.Model.DTO.FacturaDTO;
import com.utn.tsp.proyectofinal.Model.Factura;
import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import com.utn.tsp.proyectofinal.Repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import com.itextpdf.text.*;

import javax.mail.MessagingException;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    FacturaDetalleService facturaDetalleService;

    @Autowired
    ProductoService productoService;

    @Autowired
    FacturaMailService facturaMailService;

    @Autowired
    ArchivoService archivoService;

    /**
     *
     * @return
     */
    public List<Factura> getFacturas() {
        return facturaRepository.findAllByOrderByFacturaIdDesc();
    }

    /**
     *
     * @param facturaId
     * @return
     */
    public Optional<Factura> getFacturaById(Long facturaId) {
        return facturaRepository.findById(facturaId);
    }

    /**
     *
     * @param fechaDesde
     * @param fechaHasta
     * @return
     */
    public List<Factura> getFacturasByFechaDesdeFechaHasta(Date fechaDesde, Date fechaHasta) {
        return facturaRepository.findFacturaByFechaBetween(fechaDesde, fechaHasta);
    }

    /**
     *
     * @param facturaDTO
     * @return
     */
    public FacturaDTO saveFactura(FacturaDTO facturaDTO) {
        try {
            Factura factura = facturaRepository.save(facturaDTO.getFactura());
            for (FacturaDetalle facturaDetalle : facturaDTO.getFacturaDetalles()) {
                facturaDetalle.setFactura(factura);
                this.facturaDetalleService.saveOrUpdateFacturaDetalle(facturaDetalle);
                Long nuevoStock = facturaDetalle.getProducto().getExistencias() - facturaDetalle.getCantidad();
                facturaDetalle.getProducto().setExistencias(nuevoStock);
                this.productoService.saveOrUpdateProducto(facturaDetalle.getProducto());
            }
            this.generateFacturaPDF(factura);
            return this.buildFacturaDTO(factura, facturaDTO.getFacturaDetalles());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param factura
     * @param facturaDetalles
     * @return
     */
    private FacturaDTO buildFacturaDTO(Factura factura, List<FacturaDetalle> facturaDetalles) {
        FacturaDTO facturaDTO = new FacturaDTO(factura, facturaDetalles);
        return facturaDTO;
    }

    /**
     *
     * @param factura
     * @throws IOException
     * @throws DocumentException
     */
    private void generateFacturaPDF(Factura factura) throws IOException, DocumentException {
        List<FacturaDetalle> facturaDetalles = facturaDetalleService.getFacturaDetallesByFacturaId(factura.getFacturaId());
        Document document = new Document();
        Image imagen = Image.getInstance("src//main//resources//images//malva-logo.jpg");
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        String nombreDocumento = "src//main//resources//facturas//" + factura.getFacturaId() + "_factura.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(nombreDocumento));
        document.open();
        imagen.scaleAbsolute(170f, 150f);
        imagen.setAbsolutePosition(0f, 670f);
        Paragraph parrafo1 = new Paragraph("MALVA - LOVE FOR CLOTHES\n" +
                "CUIT: 27-25000987-0\n" +
                "Teléfono: 261-6909044\n" +
                "Dirección: Colón 966 Godoy Cruz, Mendoza\n" +
                "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(factura.getFecha()) + "\n" +
                "Factura #" + factura.getFacturaId() + "\n", font);
        parrafo1.setAlignment(Element.ALIGN_RIGHT);
        Chunk tituloTabla = new Chunk("DETALLE DE SU COMPRA", font);
        PdfPTable tabla = new PdfPTable(3);
        this.addTableHeader(tabla);
        this.addRows(tabla, facturaDetalles);
        Double subtotal = this.getSubtotal(facturaDetalles);
        Double descuento = (subtotal * factura.getPorcentajeDescuento()) / 100;
        Double total = subtotal - descuento;
        Paragraph parrafo2 = new Paragraph("Descuento: $" + descuento + "\n" +
                "Total: $" + total + "\n" +
                "Gracias por su compra!", font);
        document.add(imagen);
        document.add(parrafo1);
        document.add(tituloTabla);
        document.add(tabla);
        document.add(parrafo2);
        document.close();
    }

    /**
     *
     * @param tabla
     */
    private void addTableHeader(PdfPTable tabla) {
        Stream.of("Cantidad", "Producto", "Precio Unitario").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            tabla.addCell(header);
        });
    }

    /**
     *
     * @param tabla
     * @param facturaDetalles
     */
    private void addRows(PdfPTable tabla, List<FacturaDetalle> facturaDetalles) {
        for (FacturaDetalle facturaDetalle : facturaDetalles) {
            tabla.addCell(String.valueOf(facturaDetalle.getCantidad()));
            tabla.addCell(facturaDetalle.getProducto().getDescripcion());
            tabla.addCell(String.valueOf(facturaDetalle.getPrecioUnitario()));
        }
    }

    /**
     *
     * @param facturaDetalles
     * @return
     */
    private Double getSubtotal(List<FacturaDetalle> facturaDetalles) {
        Double subtotal = 0.0;
        for (FacturaDetalle facturaDetalle : facturaDetalles) {
            subtotal += facturaDetalle.getPrecioUnitario() * facturaDetalle.getCantidad();
        }
        return subtotal;
    }

    /**
     *
     * @param facturaId
     * @param mailTo
     * @return
     * @throws MessagingException
     */
    public boolean sendMail(String facturaId, String mailTo) throws MessagingException {
        return facturaMailService.sendEmail(mailTo, facturaId);
    }

    /**
     *
     * @param facturaId
     * @return
     */
    public Resource getFacturaPDF(Long facturaId) {
        String fileName = facturaId + "_factura.pdf";
        return this.archivoService.load(fileName, "PDF");
    }

}
