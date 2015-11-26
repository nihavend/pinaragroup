/*
 * XML Type:  odiExtProperties
 * Namespace: http://www.likyateknoloji.com/odi-ext
 * Java type: com.likya.xsd.myra.ext.odi.OdiExtProperties
 *
 * Automatically generated - do not modify.
 */
package com.likya.xsd.myra.ext.odi.impl;
/**
 * An XML odiExtProperties(@http://www.likyateknoloji.com/odi-ext).
 *
 * This is a complex type.
 */
public class OdiExtPropertiesImpl extends com.likya.xsd.myra.model.joblist.impl.AbstractJobTypeImpl implements com.likya.xsd.myra.ext.odi.OdiExtProperties
{
    private static final long serialVersionUID = 1L;
    
    public OdiExtPropertiesImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ODIEXTPARAMS$0 = 
        new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "odiExtParams");
    
    
    /**
     * Gets the "odiExtParams" element
     */
    public com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams getOdiExtParams()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams target = null;
            target = (com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams)get_store().find_element_user(ODIEXTPARAMS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "odiExtParams" element
     */
    public void setOdiExtParams(com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams odiExtParams)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams target = null;
            target = (com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams)get_store().find_element_user(ODIEXTPARAMS$0, 0);
            if (target == null)
            {
                target = (com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams)get_store().add_element_user(ODIEXTPARAMS$0);
            }
            target.set(odiExtParams);
        }
    }
    
    /**
     * Appends and returns a new empty "odiExtParams" element
     */
    public com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams addNewOdiExtParams()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams target = null;
            target = (com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams)get_store().add_element_user(ODIEXTPARAMS$0);
            return target;
        }
    }
}
