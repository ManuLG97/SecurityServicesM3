package securityservices.shared.products.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import securityservices.products.Product;
import static org.junit.Assert.*;
import securityservices.products.Equipment;
import securityservices.shared.responses.ResultRequest;

/**
 *
 * @author Sergio
 */
public class EquipmentTest {
    private Equipment equipment;
    public EquipmentTest() {
        
    }
          
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
          
   
    ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 8.0,10.06, true, "fucntion", "5 componets", 1);
     
    if(result.failed()){
        equipment = null;
     } else {equipment = result.getValue();
    }
    
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setAvailable method, of class Product.

    @Test
    public void testSetAvailable() {
        System.out.println("setAvailable");
       
       
    }
    /**
     * Test of isAvailable method, of class Product.
    
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        Product instance = new ProductImpl();
        Boolean expResult = null;
        Boolean result = instance.isAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of getCode method, of class Product.
     */
    @Test
    public void testGetCodeEmpty() {
 System.out.println("getName");
        ResultRequest<Equipment> result = Equipment.getInstance("", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Code no deberia estar vacio", true, result.failed()); 
    }
    
        /**
     * Test of setCode method, of class Product.
     */
    @Test
    public void testSetCodeEmpty() {
        System.out.println("setCode");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setCode("    ");
         assertEquals("No deberia de estar en blanco", true, result.failed());
    }

    
        /**
     * Test of getCode method, of class Product.
     */
    @Test
    public void testGetCodeNull() {
 System.out.println("getName");
        ResultRequest<Equipment> result = Equipment.getInstance(null , "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El campo code no deberia ser null", true, result.failed()); 
    }
    
        /**
     * Test of setCode method, of class Product.
     */
    @Test
    public void testSetCodeNull() {
        System.out.println("setCode");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setCode(null);
         assertEquals("No deberia de estar en blanco", true, result.failed());
    }



    
    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetNameEmpty() {
        System.out.println("getName");
        ResultRequest<Equipment> result = Equipment.getInstance("001", "", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Name no deberia estar vacio", true, result.failed());    
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetNameEmpty() {
        System.out.println("setName");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setName("    ");
         assertEquals("No deberia de estar en blanco", true, result.failed());
    }
    
        /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetNameNull() {
        System.out.println("getName");
        ResultRequest<Equipment> result = Equipment.getInstance("001", null, "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Name no deberia ser null", true, result.failed());    
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetNameNull() {
        System.out.println("setName");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setName(null);
         assertEquals("No deberia de estar en null", true, result.failed());
    }
    
    /**
     * Test of setPrice method, of class Product.
    */
    @Test
    public void testSetPriceMayorZero() {
        System.out.println("setPriceMayorZero");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setPrice(0.0);
         assertEquals("No deberia de ser 0.0 sino mayor ", true, result.failed());
  
    }
    
     @Test
    public void testGetPriceMayorZero() {
        System.out.println("getPriceZero");
        ResultRequest<Equipment> result = Equipment.getInstance("001", null, "Type", "Manu S.L", "Venta 1", 0.0, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Price deberia ser mayor a 0", true, result.failed());    
    }
    
       /**
     * Test of setPrice method, of class Product.
    */
    @Test
    public void testSetPriceMenorZero() {
        System.out.println("setPriceMayorZero");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setPrice(-1.0);
         assertEquals("No deberia de ser menor que 0.1 sino igual o mayor ", true, result.failed());
  
    }
    
     @Test
    public void testGetPriceMenorZero() {
        System.out.println("getPriceMenorZero");
        ResultRequest<Equipment> result = Equipment.getInstance("001", null, "Type", "Manu S.L", "Venta 1", -0.1, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Price no deberia ser menor a 0", true, result.failed());    
    }
    
   
      
   /**
    * Test deep
    */ 
    @Test
    public void testNegativeDeep() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, -2.0,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("Deep no puedes ser negativo", true, result.failed()); 
    }
    
    @Test
    public void testSetNegativeDeep() {
        ResultRequest result = equipment.setDeep(-4.2);
        assertEquals("Deep no puedes ser negativo", true, result.failed()); 
      }
    
    @Test
    public void testNullDeep() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9,null,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("Deep no puedes ser null", true, result.failed()); 
    }
    
    @Test
    public void testSetNullDeep() {
        ResultRequest result = equipment.setDeep(null);
        assertEquals("Deep no puedes ser negativo", true, result.failed()); 
      }
    
    /*
    /Test weight
    */
     @Test
    public void testNegativeWeight() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, -2.0,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("Weight no puedes ser negativo", true, result.failed()); 
    }
    
    @Test
    public void testSetNegativeWeight() {
        ResultRequest result = equipment.setWeight(-4.2);
        assertEquals("Weight no puedes ser negativo", true, result.failed()); 
      }
    
    @Test
    public void testNullWeight() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, 9.9, 12.0,null, true, "fucntion", "5 componets", 1);
        assertEquals("Weight no puedes ser null", true, result.failed()); 
    }
    
    @Test
    public void testSetNullWeight() {
        ResultRequest result = equipment.setWeight(null);
        assertEquals("Weight no puedes ser negativo", true, result.failed()); 
      }
    
     /*
    /Test wide
    */
     @Test
    public void testNegativeWide() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, -9.9, 12.0,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("Wide no puedes ser negativo", true, result.failed()); 
    }
    
    @Test
    public void testSetNegativeWide() {
        ResultRequest result = equipment.setWeight(-4.2);
        assertEquals("Wide no puedes ser negativo", true, result.failed()); 
      }
    
    @Test
    public void testNullWide() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, 8.88, null, 12.0,12.00, true, "fucntion", "5 componets", 1);
        assertEquals("Wide no puedes ser null", true, result.failed()); 
    }
    
    @Test
    public void testSetNullWide() {
        ResultRequest result = equipment.setWeight(null);
        assertEquals("Wide no puedes ser negativo", true, result.failed()); 
      }
    
     /*
    /Test High
    */
     @Test
    public void testNegativeHigh() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, -8.88, 9.9, 12.0,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("High no puedes ser negativo", true, result.failed()); 
    }
    
    @Test
    public void testSetNegativeHigh() {
        ResultRequest result = equipment.setWeight(-8.9);
        assertEquals("High no puedes ser negativo", true, result.failed()); 
      }
    
    @Test
    public void testNullHigh() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, null, 12.33, 12.0,12.00, true, "fucntion", "5 componets", 1);
        assertEquals("High no puedes ser null", true, result.failed()); 
    }
    
    @Test
    public void testSetNullHigh() {
        ResultRequest result = equipment.setWeight(null);
        assertEquals("High no puedes ser negativo", true, result.failed()); 
      }
    
      
     /*
    /Test Power
    */
     @Test
    public void testNegativePower() {
        ResultRequest<Equipment> result = Equipment.getInstance("Manu", "Servicio 10", "Type", "Manu S.L", "Venta 1", 8.88, -8.88, 9.9, 12.0,10.06, true, "fucntion", "5 componets", -1);
        assertEquals("High no puedes ser negativo", true, result.failed()); 
    }
    
    @Test
    public void testSetNegativePower() {
        ResultRequest result = equipment.setPower(-8);
        assertEquals("Power no puedes ser negativo", true, result.failed()); 
      }
    
        /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetMakerEmpty() {
        System.out.println("getMaker");
        ResultRequest<Equipment> result = Equipment.getInstance("001", "Manu S.L", "Type", "", "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Maker no deberia estar vacio", true, result.failed());    
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetMakerEmpty() {
        System.out.println("setMaker");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setMaker("    ");
         assertEquals("No deberia de estar en blanco", true, result.failed());
    }
    
        /**
     * Test of getMaker null
     */
    @Test
    public void testGetMakerNull() {
        System.out.println("getMaker");
        ResultRequest<Equipment> result = Equipment.getInstance("001", "Manu", "Type", null, "Venta 1", 8.88, 8.88, 9.9, 9.9,10.06, true, "fucntion", "5 componets", 1);
        assertEquals("El tipo Maker no deberia ser null", true, result.failed());    
    }

    /**
     * Test of setMaker method null
     */
    @Test
    public void testSetMakerNull() {
        System.out.println("setMaker");
         Equipment equipment = new Equipment();
         ResultRequest<Equipment> result = equipment.setMaker(null);
         assertEquals("No deberia de estar en null", true, result.failed());
    }
 
    
}
