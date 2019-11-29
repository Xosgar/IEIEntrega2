package Webs;

import modelos.Movil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Fnac {
//da una exxecpcion en el elemento 10 pero no se porque, no veo el fallo de ese elemento
    public static void main(String[] args ){
    Chrome("Apple", "Iphone");
    }
    private static WebDriver driver= null;

    public static List<Movil> Chrome(String marca, String modelo){
        List<Movil> res = new ArrayList<Movil>();
        String exePath = "chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        //a VECES SALE AMAZON captchat y no se puede avanzar
        driver.get("https://www.fnac.es/");

        WebElement cajaBusqueda = driver.findElement(By.xpath("//*[@id=\"Fnac_Search\"]"));
        //el sendKey va a venir del parametro de la interfaz en el imput
        cajaBusqueda.sendKeys(modelo + " " + marca + Keys.ENTER);
        waitForPageLoaded();

        WebElement departamento = driver.findElement(By.xpath("//span[contains(@class,'Affine-link lienInverse') and contains(text(),'Tecnología')]"));
        departamento.click();
        waitForPageLoaded();

        WebElement filtroMoviles = driver.findElement(By.xpath("//span[contains(@class,'noir lienInverse') and contains(text(),'Smartphones y teléfonos móviles')]"));
        filtroMoviles.click();
        waitForPageLoaded();

        /*WebElement filtroMarca = driver.findElement(By.xpath("//span[contains(@class,'Filters-choice js-Filters-choice isActive') and contains(@data-noaccent,'" + marca + "')]"));
        filtroMarca.click();
        waitForPageLoaded();*/

        List<WebElement> listaElementos =
                driver.findElements(By.xpath("//*[contains(@class, 'clearfix Article-item js-Search-hashLinkId')]"));
        System.out.println("Numero de elementos de la lista: " + listaElementos.size());

        //hace falta añadir el filtro por marca e tipo de articulo y obtener los datos



        WebElement elementoActual, nombreProducto, precio,descuento,imagen/*,categoria,marca*/;
        String nombreP,precioP,descuentoP,imagenP,categoriaP,marcaP;
        int j=1;
        for (int i=0; i<listaElementos.size(); i++)
        {
            elementoActual = listaElementos.get(i);

            nombreProducto = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[2]/div/p[1]/a"));
            nombreP= nombreProducto.getText();
            imagen = elementoActual.findElement(By.xpath("//html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[1]/img"));
            imagenP = imagen.getAttribute("src");

            try {
                precio = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                        j + "]/article/div[3]/div/div/div/div/div[3]/span[2]"));
                precioP = precio.getText();

                Movil movilActual = new Movil(nombreP,precioP,imagenP,marca);
                res.add(movilActual);
                System.out.println(movilActual.toString());
            }catch(Exception noSocio) {
                try{
                    precio = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                        j + "]/article/div[3]/div/div/div/div/div[1]/a/strong"));
                    precioP = precio.getText();
                    try{descuento = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                        j + "]/article/div[3]/div/div/div/div/div[1]/del"));
                    descuentoP = descuento.getText();

                    Movil movilActual = new Movil(nombreP,precioP,descuentoP,imagenP,marca);
                    res.add(movilActual);
                    System.out.println(movilActual.toString());
                    }catch(Exception noSocioNoDescuento) {
                        Movil movilActual = new Movil(nombreP,precioP,imagenP,marca);
                        res.add(movilActual);
                        System.out.println(movilActual.toString());
                    }

                }catch(Exception tipo2) {
                    try{
                        precio = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                                j + "]/article/div[3]/div/div/div/div/div[1]/a"));
                        precioP = precio.getText();
                        try {
                            descuento = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                                    j + "]/article/div[3]/div/div/div/div/div[1]/span"));
                            descuentoP = descuento.getText();

                            Movil movilActual = new Movil(nombreP, precioP, descuentoP, imagenP, marca);
                            res.add(movilActual);
                            System.out.println(movilActual.toString());
                        }catch(Exception tipo2NoDescuento) {
                            Movil movilActual = new Movil(nombreP,precioP,imagenP,marca);
                            res.add(movilActual);
                            System.out.println(movilActual.toString());
                        }
                    }catch(Exception tipo1) {
                        precio = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                                j + "]/article/div[3]/div/div/div/div[1]/a/strong"));
                        precioP = precio.getText();
                        try {
                            descuento = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div[" +
                                    j + "]/article/div[3]/div/div/div/div[1]/del"));
                            descuentoP = descuento.getText();

                            Movil movilActual = new Movil(nombreP, precioP, descuentoP, imagenP, marca);
                            res.add(movilActual);
                            System.out.println(movilActual.toString());
                        }catch(Exception tipo1NoDescuento) {
                            Movil movilActual = new Movil(nombreP,precioP,imagenP,marca);
                            res.add(movilActual);
                            System.out.println(movilActual.toString());
                        }
                    }

                }
            }
            /*descuento = elementoActual.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+j+"]/article/div[3]/div/div/div/div/div[1]/del"));
            descuentoP = descuento.getText();*/




                /*try{
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
               }*/




                /*try{
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
                }*/

            j++;
        }

        return res;
    }

    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }

}
