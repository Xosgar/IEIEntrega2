package modelos;

import java.util.Objects;

public class Movil {
    private String nombre;
    private String precioActual;
    private String precioAntesDescuento;
    private String urlImagen;
    private String marca;

    public Movil(String nombre, String precioActual, String precioAntesDescuento, String urlImagen, String marca) {
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.precioAntesDescuento = precioAntesDescuento;
        this.urlImagen = urlImagen;
        this.marca = marca;
    }

    public Movil(String nombre, String precioActual, String urlImagen, String marca) {
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.urlImagen = urlImagen;
        this.precioAntesDescuento = precioActual;
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(String precioActual) {
        this.precioActual = precioActual;
    }

    public String getPrecioAntesDescuento() {
        return precioAntesDescuento;
    }

    public void setPrecioAntesDescuento(String precioAntesDescuento) {
        this.precioAntesDescuento = precioAntesDescuento;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double Descuento(){
        String nuevoPrecio = this.getPrecioActual().replace(".","");
        String nuevoPrecio3 = this.getPrecioAntesDescuento().replace(".","");
        String nuevoPrecio2 = nuevoPrecio.replace(",",".");
        String nuevoPrecio4 = nuevoPrecio3.replace(",",".");
        Double aux1 = new Double(nuevoPrecio2);
        Double aux2 = new Double(nuevoPrecio4);
        return  (aux1)/(aux2)*100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movil)) return false;
        Movil movil = (Movil) o;
        return getNombre().equals(movil.getNombre()) &&
                getMarca().equals(movil.getMarca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getMarca());
    }

    @Override
    public String toString() {
        return nombre +
                " Precio: " + precioActual  +
                " Descuento: " + this.Descuento() + " %"
                ;
    }
}
