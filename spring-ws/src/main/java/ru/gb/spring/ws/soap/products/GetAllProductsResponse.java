//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.14 at 03:33:41 AM MSK 
//


package ru.gb.spring.ws.soap.products;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "products"
})
@XmlRootElement(name = "getAllProductsResponse")
public class GetAllProductsResponse {

    @XmlElement(required = true)
    protected List<Product> products;

    /**
     * Gets the value of the products property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the products property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Product }
     *
     *
     */
    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<Product>();
        }
        return this.products;
    }

}
