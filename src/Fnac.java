import modelos.Movil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Fnac {
//da una exxecpcion en el elemento 10 pero no se porque, no veo el fallo de ese elemento
    public static void main(String[] args ){
    Chrome("Iphone");
    }
    private static WebDriver driver= null;

    public static void Chrome(String nombre){
        String exePath = "chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        //a VECES SALE AMAZON captchat y no se puede avanzar
        driver.get("https://www.fnac.es/");
//

        WebElement cajaBusqueda = driver.findElement(By.xpath("//*[@id=\"Fnac_Search\"]"));
        //el sendKey va a venir del parametro de la interfaz en el imput
        cajaBusqueda.sendKeys(nombre+ Keys.ENTER);

        List<WebElement> listaElementos =
                driver.findElements(By.xpath("//*[contains(@class, 'clearfix Article-item js-Search-hashLinkId')]"));
        System.out.println("Numero de elementos de la lista: " + listaElementos.size());

        //hace falta añadir el filtro por marca e tipo de articulo y obtener los datos



        WebElement elementoActual, nombreProducto, precio,descuento,imagen,categoria,marca;
        String nombreP,precioP,descuentoP,imagenP,categoriaP,marcaP;
        int j=1;
        for (int i=0; i<listaElementos.size(); i++)
        {
            elementoActual = listaElementos.get(i);
            try {
                descuento = elementoActual.
                        findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[3]/div/div/div/div/div[1]/del"));
                descuentoP = descuento.getText();
                nombreProducto =
                        elementoActual.
                                findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[1]/a"));
                nombreP= nombreProducto.getText();
                precio = elementoActual.
                        findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[3]/div/div/div/div/div[1]/a/strong"));
                precioP = precio.getText();

                imagen = elementoActual
                        .findElement(By.xpath("//html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[1]/img"));
                imagenP = imagen.getAttribute("src");

                try{
                    categoria = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[2]/a")) ;
                    categoriaP= categoria.getText();
                }catch (Exception err2){
                    categoria = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[2]")) ;
                    categoriaP= categoria.getText();
                }

               try {
                   marca = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/div/div[1]/div/ul/li/span[2]/span"));
                   marcaP = marca.getText();
               }catch(Exception err){
                   marca = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" + j + "]/article/div[2]/div/p[2]/span[1]/a"));
                   marcaP = marca.getText();
               }

                Movil movilActual = new Movil(nombreP,precioP,descuentoP,imagenP,marcaP);

                System.out.println(movilActual.toString());
            }catch (Exception err){
                nombreProducto =
                        elementoActual.
                                findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[1]/a"));
                nombreP= nombreProducto.getText();
                precio = elementoActual.
                        findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+ "]/article/div[3]/div/div/div/div[1]/a/strong"));
                precioP = precio.getText();

                imagen = elementoActual
                        .findElement(By.xpath("//html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[1]/img"));
                imagenP = imagen.getAttribute("src");


                try{
                    categoria = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[2]/a")) ;
                    categoriaP= categoria.getText();
                }catch (Exception err2){
                    categoria = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[2]")) ;
                    categoriaP= categoria.getText();
                }



                try {
                    marca = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/div/div[1]/div/ul/li/span[2]/span"));
                    marcaP = marca.getText();
                }catch(Exception err2){
                    marca = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" + j + "]/article/div[2]/div/p[2]/span[1]/a"));
                    marcaP = marca.getText();
                }

                Movil movilActual = new Movil(nombreP,precioP,imagenP,marcaP);

                System.out.println(movilActual.toString());
            }
            j++;
        }


    }

}
