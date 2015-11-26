/*
 * XML Type:  odiExtProperties
 * Namespace: http://www.likyateknoloji.com/odi-ext
 * Java type: com.likya.xsd.myra.ext.odi.OdiExtProperties
 *
 * Automatically generated - do not modify.
 */
package com.likya.xsd.myra.ext.odi;


/**
 * An XML odiExtProperties(@http://www.likyateknoloji.com/odi-ext).
 *
 * This is a complex type.
 */
public interface OdiExtProperties extends com.likya.xsd.myra.model.joblist.AbstractJobType
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(OdiExtProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s108AF8A7D97B4CAB0FB878DCB163712D").resolveHandle("odiextproperties2aa5type");
    
    /**
     * Gets the "odiExtParams" element
     */
    com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams getOdiExtParams();
    
    /**
     * Sets the "odiExtParams" element
     */
    void setOdiExtParams(com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams odiExtParams);
    
    /**
     * Appends and returns a new empty "odiExtParams" element
     */
    com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams addNewOdiExtParams();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties newInstance() {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.likya.xsd.myra.ext.odi.OdiExtProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.likya.xsd.myra.ext.odi.OdiExtProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
