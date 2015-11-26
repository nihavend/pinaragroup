/*
 * An XML document type.
 * Localname: odiExtParams
 * Namespace: http://www.likyateknoloji.com/odi-ext
 * Java type: com.likya.xsd.myra.ext.odi.OdiExtParamsDocument
 *
 * Automatically generated - do not modify.
 */
package com.likya.xsd.myra.ext.odi.impl;
/**
 * A document containing one odiExtParams(@http://www.likyateknoloji.com/odi-ext) element.
 *
 * This is a complex type.
 */
public class OdiExtParamsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.likya.xsd.myra.ext.odi.OdiExtParamsDocument
{
    private static final long serialVersionUID = 1L;
    
    public OdiExtParamsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
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
    /**
     * An XML odiExtParams(@http://www.likyateknoloji.com/odi-ext).
     *
     * This is a complex type.
     */
    public static class OdiExtParamsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams
    {
        private static final long serialVersionUID = 1L;
        
        public OdiExtParamsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PJDBCURL$0 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pJdbcUrl");
        private static final javax.xml.namespace.QName PJDBCDRIVER$2 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pJdbcDriver");
        private static final javax.xml.namespace.QName PJDBCUSERNAME$4 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pJdbcUsername");
        private static final javax.xml.namespace.QName PJDBCPASSWORD$6 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pJdbcPassword");
        private static final javax.xml.namespace.QName PWORKNAME$8 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pWorkName");
        private static final javax.xml.namespace.QName PUSERNAME$10 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pUsername");
        private static final javax.xml.namespace.QName PPASSWORD$12 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pPassword");
        private static final javax.xml.namespace.QName PAGENTURL$14 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pAgentUrl");
        private static final javax.xml.namespace.QName PSCENNAME$16 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pScenName");
        private static final javax.xml.namespace.QName PSCENVERSION$18 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pScenVersion");
        private static final javax.xml.namespace.QName PKEYWORDS$20 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pKeywords");
        private static final javax.xml.namespace.QName PCONTEXTCODE$22 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pContextCode");
        private static final javax.xml.namespace.QName PLOGLEVEL$24 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pLogLevel");
        private static final javax.xml.namespace.QName PSESSIONNAME$26 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pSessionName");
        private static final javax.xml.namespace.QName PSYNCHRONOUS$28 = 
            new javax.xml.namespace.QName("http://www.likyateknoloji.com/odi-ext", "pSynchronous");
        
        
        /**
         * Gets the "pJdbcUrl" element
         */
        public java.lang.String getPJdbcUrl()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCURL$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pJdbcUrl" element
         */
        public org.apache.xmlbeans.XmlString xgetPJdbcUrl()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCURL$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pJdbcUrl" element
         */
        public void setPJdbcUrl(java.lang.String pJdbcUrl)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCURL$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PJDBCURL$0);
                }
                target.setStringValue(pJdbcUrl);
            }
        }
        
        /**
         * Sets (as xml) the "pJdbcUrl" element
         */
        public void xsetPJdbcUrl(org.apache.xmlbeans.XmlString pJdbcUrl)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCURL$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PJDBCURL$0);
                }
                target.set(pJdbcUrl);
            }
        }
        
        /**
         * Gets the "pJdbcDriver" element
         */
        public java.lang.String getPJdbcDriver()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCDRIVER$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pJdbcDriver" element
         */
        public org.apache.xmlbeans.XmlString xgetPJdbcDriver()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCDRIVER$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pJdbcDriver" element
         */
        public void setPJdbcDriver(java.lang.String pJdbcDriver)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCDRIVER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PJDBCDRIVER$2);
                }
                target.setStringValue(pJdbcDriver);
            }
        }
        
        /**
         * Sets (as xml) the "pJdbcDriver" element
         */
        public void xsetPJdbcDriver(org.apache.xmlbeans.XmlString pJdbcDriver)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCDRIVER$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PJDBCDRIVER$2);
                }
                target.set(pJdbcDriver);
            }
        }
        
        /**
         * Gets the "pJdbcUsername" element
         */
        public java.lang.String getPJdbcUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCUSERNAME$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pJdbcUsername" element
         */
        public org.apache.xmlbeans.XmlString xgetPJdbcUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCUSERNAME$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pJdbcUsername" element
         */
        public void setPJdbcUsername(java.lang.String pJdbcUsername)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCUSERNAME$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PJDBCUSERNAME$4);
                }
                target.setStringValue(pJdbcUsername);
            }
        }
        
        /**
         * Sets (as xml) the "pJdbcUsername" element
         */
        public void xsetPJdbcUsername(org.apache.xmlbeans.XmlString pJdbcUsername)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCUSERNAME$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PJDBCUSERNAME$4);
                }
                target.set(pJdbcUsername);
            }
        }
        
        /**
         * Gets the "pJdbcPassword" element
         */
        public java.lang.String getPJdbcPassword()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCPASSWORD$6, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pJdbcPassword" element
         */
        public org.apache.xmlbeans.XmlString xgetPJdbcPassword()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCPASSWORD$6, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pJdbcPassword" element
         */
        public void setPJdbcPassword(java.lang.String pJdbcPassword)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PJDBCPASSWORD$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PJDBCPASSWORD$6);
                }
                target.setStringValue(pJdbcPassword);
            }
        }
        
        /**
         * Sets (as xml) the "pJdbcPassword" element
         */
        public void xsetPJdbcPassword(org.apache.xmlbeans.XmlString pJdbcPassword)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PJDBCPASSWORD$6, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PJDBCPASSWORD$6);
                }
                target.set(pJdbcPassword);
            }
        }
        
        /**
         * Gets the "pWorkName" element
         */
        public java.lang.String getPWorkName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PWORKNAME$8, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pWorkName" element
         */
        public org.apache.xmlbeans.XmlString xgetPWorkName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PWORKNAME$8, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pWorkName" element
         */
        public void setPWorkName(java.lang.String pWorkName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PWORKNAME$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PWORKNAME$8);
                }
                target.setStringValue(pWorkName);
            }
        }
        
        /**
         * Sets (as xml) the "pWorkName" element
         */
        public void xsetPWorkName(org.apache.xmlbeans.XmlString pWorkName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PWORKNAME$8, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PWORKNAME$8);
                }
                target.set(pWorkName);
            }
        }
        
        /**
         * Gets the "pUsername" element
         */
        public java.lang.String getPUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PUSERNAME$10, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pUsername" element
         */
        public org.apache.xmlbeans.XmlString xgetPUsername()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PUSERNAME$10, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pUsername" element
         */
        public void setPUsername(java.lang.String pUsername)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PUSERNAME$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PUSERNAME$10);
                }
                target.setStringValue(pUsername);
            }
        }
        
        /**
         * Sets (as xml) the "pUsername" element
         */
        public void xsetPUsername(org.apache.xmlbeans.XmlString pUsername)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PUSERNAME$10, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PUSERNAME$10);
                }
                target.set(pUsername);
            }
        }
        
        /**
         * Gets the "pPassword" element
         */
        public java.lang.String getPPassword()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PPASSWORD$12, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pPassword" element
         */
        public org.apache.xmlbeans.XmlString xgetPPassword()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PPASSWORD$12, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pPassword" element
         */
        public void setPPassword(java.lang.String pPassword)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PPASSWORD$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PPASSWORD$12);
                }
                target.setStringValue(pPassword);
            }
        }
        
        /**
         * Sets (as xml) the "pPassword" element
         */
        public void xsetPPassword(org.apache.xmlbeans.XmlString pPassword)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PPASSWORD$12, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PPASSWORD$12);
                }
                target.set(pPassword);
            }
        }
        
        /**
         * Gets the "pAgentUrl" element
         */
        public java.lang.String getPAgentUrl()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGENTURL$14, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pAgentUrl" element
         */
        public org.apache.xmlbeans.XmlString xgetPAgentUrl()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGENTURL$14, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pAgentUrl" element
         */
        public void setPAgentUrl(java.lang.String pAgentUrl)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAGENTURL$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PAGENTURL$14);
                }
                target.setStringValue(pAgentUrl);
            }
        }
        
        /**
         * Sets (as xml) the "pAgentUrl" element
         */
        public void xsetPAgentUrl(org.apache.xmlbeans.XmlString pAgentUrl)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PAGENTURL$14, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PAGENTURL$14);
                }
                target.set(pAgentUrl);
            }
        }
        
        /**
         * Gets the "pScenName" element
         */
        public java.lang.String getPScenName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSCENNAME$16, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pScenName" element
         */
        public org.apache.xmlbeans.XmlString xgetPScenName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSCENNAME$16, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pScenName" element
         */
        public void setPScenName(java.lang.String pScenName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSCENNAME$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSCENNAME$16);
                }
                target.setStringValue(pScenName);
            }
        }
        
        /**
         * Sets (as xml) the "pScenName" element
         */
        public void xsetPScenName(org.apache.xmlbeans.XmlString pScenName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSCENNAME$16, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSCENNAME$16);
                }
                target.set(pScenName);
            }
        }
        
        /**
         * Gets the "pScenVersion" element
         */
        public java.lang.String getPScenVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSCENVERSION$18, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pScenVersion" element
         */
        public org.apache.xmlbeans.XmlString xgetPScenVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSCENVERSION$18, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pScenVersion" element
         */
        public void setPScenVersion(java.lang.String pScenVersion)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSCENVERSION$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSCENVERSION$18);
                }
                target.setStringValue(pScenVersion);
            }
        }
        
        /**
         * Sets (as xml) the "pScenVersion" element
         */
        public void xsetPScenVersion(org.apache.xmlbeans.XmlString pScenVersion)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSCENVERSION$18, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSCENVERSION$18);
                }
                target.set(pScenVersion);
            }
        }
        
        /**
         * Gets the "pKeywords" element
         */
        public java.lang.String getPKeywords()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PKEYWORDS$20, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pKeywords" element
         */
        public org.apache.xmlbeans.XmlString xgetPKeywords()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PKEYWORDS$20, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pKeywords" element
         */
        public void setPKeywords(java.lang.String pKeywords)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PKEYWORDS$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PKEYWORDS$20);
                }
                target.setStringValue(pKeywords);
            }
        }
        
        /**
         * Sets (as xml) the "pKeywords" element
         */
        public void xsetPKeywords(org.apache.xmlbeans.XmlString pKeywords)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PKEYWORDS$20, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PKEYWORDS$20);
                }
                target.set(pKeywords);
            }
        }
        
        /**
         * Gets the "pContextCode" element
         */
        public java.lang.String getPContextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PCONTEXTCODE$22, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pContextCode" element
         */
        public org.apache.xmlbeans.XmlString xgetPContextCode()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PCONTEXTCODE$22, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pContextCode" element
         */
        public void setPContextCode(java.lang.String pContextCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PCONTEXTCODE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PCONTEXTCODE$22);
                }
                target.setStringValue(pContextCode);
            }
        }
        
        /**
         * Sets (as xml) the "pContextCode" element
         */
        public void xsetPContextCode(org.apache.xmlbeans.XmlString pContextCode)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PCONTEXTCODE$22, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PCONTEXTCODE$22);
                }
                target.set(pContextCode);
            }
        }
        
        /**
         * Gets the "pLogLevel" element
         */
        public java.math.BigInteger getPLogLevel()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGLEVEL$24, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "pLogLevel" element
         */
        public org.apache.xmlbeans.XmlInteger xgetPLogLevel()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(PLOGLEVEL$24, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pLogLevel" element
         */
        public void setPLogLevel(java.math.BigInteger pLogLevel)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGLEVEL$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PLOGLEVEL$24);
                }
                target.setBigIntegerValue(pLogLevel);
            }
        }
        
        /**
         * Sets (as xml) the "pLogLevel" element
         */
        public void xsetPLogLevel(org.apache.xmlbeans.XmlInteger pLogLevel)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(PLOGLEVEL$24, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(PLOGLEVEL$24);
                }
                target.set(pLogLevel);
            }
        }
        
        /**
         * Gets the "pSessionName" element
         */
        public java.lang.String getPSessionName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSESSIONNAME$26, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pSessionName" element
         */
        public org.apache.xmlbeans.XmlString xgetPSessionName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSESSIONNAME$26, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pSessionName" element
         */
        public void setPSessionName(java.lang.String pSessionName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSESSIONNAME$26, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSESSIONNAME$26);
                }
                target.setStringValue(pSessionName);
            }
        }
        
        /**
         * Sets (as xml) the "pSessionName" element
         */
        public void xsetPSessionName(org.apache.xmlbeans.XmlString pSessionName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSESSIONNAME$26, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSESSIONNAME$26);
                }
                target.set(pSessionName);
            }
        }
        
        /**
         * Gets the "pSynchronous" element
         */
        public boolean getPSynchronous()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSYNCHRONOUS$28, 0);
                if (target == null)
                {
                    return false;
                }
                return target.getBooleanValue();
            }
        }
        
        /**
         * Gets (as xml) the "pSynchronous" element
         */
        public org.apache.xmlbeans.XmlBoolean xgetPSynchronous()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(PSYNCHRONOUS$28, 0);
                return target;
            }
        }
        
        /**
         * Sets the "pSynchronous" element
         */
        public void setPSynchronous(boolean pSynchronous)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSYNCHRONOUS$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSYNCHRONOUS$28);
                }
                target.setBooleanValue(pSynchronous);
            }
        }
        
        /**
         * Sets (as xml) the "pSynchronous" element
         */
        public void xsetPSynchronous(org.apache.xmlbeans.XmlBoolean pSynchronous)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlBoolean target = null;
                target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(PSYNCHRONOUS$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(PSYNCHRONOUS$28);
                }
                target.set(pSynchronous);
            }
        }
    }
}
