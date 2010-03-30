/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.deepsky.lang.plsql.castor.mapping;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class FunctionType.
 * 
 * @version $Revision$ $Date$
 */
public class FunctionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _returnType.
     */
    private java.lang.String _returnType;

    /**
     * Field _desc.
     */
    private java.lang.String _desc;

    /**
     * Field _validatorClass.
     */
    private java.lang.String _validatorClass;

    /**
     * Field _argList.
     */
    private java.util.List _argList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FunctionType() {
        super();
        this._argList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vArg
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addArg(
            final com.deepsky.lang.plsql.castor.mapping.Arg vArg)
    throws java.lang.IndexOutOfBoundsException {
        this._argList.add(vArg);
    }

    /**
     * 
     * 
     * @param index
     * @param vArg
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addArg(
            final int index,
            final com.deepsky.lang.plsql.castor.mapping.Arg vArg)
    throws java.lang.IndexOutOfBoundsException {
        this._argList.add(index, vArg);
    }

    /**
     * Method enumerateArg.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateArg(
    ) {
        return java.util.Collections.enumeration(this._argList);
    }

    /**
     * Method getArg.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.deepsky.lang.plsql.castor.mapping.Arg at the given index
     */
    public com.deepsky.lang.plsql.castor.mapping.Arg getArg(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._argList.size()) {
            throw new IndexOutOfBoundsException("getArg: Index value '" + index + "' not in range [0.." + (this._argList.size() - 1) + "]");
        }
        
        return (com.deepsky.lang.plsql.castor.mapping.Arg) _argList.get(index);
    }

    /**
     * Method getArg.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.deepsky.lang.plsql.castor.mapping.Arg[] getArg(
    ) {
        com.deepsky.lang.plsql.castor.mapping.Arg[] array = new com.deepsky.lang.plsql.castor.mapping.Arg[0];
        return (com.deepsky.lang.plsql.castor.mapping.Arg[]) this._argList.toArray(array);
    }

    /**
     * Method getArgCount.
     * 
     * @return the size of this collection
     */
    public int getArgCount(
    ) {
        return this._argList.size();
    }

    /**
     * Returns the value of field 'desc'.
     * 
     * @return the value of field 'Desc'.
     */
    public java.lang.String getDesc(
    ) {
        return this._desc;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'returnType'.
     * 
     * @return the value of field 'ReturnType'.
     */
    public java.lang.String getReturnType(
    ) {
        return this._returnType;
    }

    /**
     * Returns the value of field 'validatorClass'.
     * 
     * @return the value of field 'ValidatorClass'.
     */
    public java.lang.String getValidatorClass(
    ) {
        return this._validatorClass;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method iterateArg.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateArg(
    ) {
        return this._argList.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllArg(
    ) {
        this._argList.clear();
    }

    /**
     * Method removeArg.
     * 
     * @param vArg
     * @return true if the object was removed from the collection.
     */
    public boolean removeArg(
            final com.deepsky.lang.plsql.castor.mapping.Arg vArg) {
        boolean removed = _argList.remove(vArg);
        return removed;
    }

    /**
     * Method removeArgAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.deepsky.lang.plsql.castor.mapping.Arg removeArgAt(
            final int index) {
        java.lang.Object obj = this._argList.remove(index);
        return (com.deepsky.lang.plsql.castor.mapping.Arg) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vArg
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setArg(
            final int index,
            final com.deepsky.lang.plsql.castor.mapping.Arg vArg)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._argList.size()) {
            throw new IndexOutOfBoundsException("setArg: Index value '" + index + "' not in range [0.." + (this._argList.size() - 1) + "]");
        }
        
        this._argList.set(index, vArg);
    }

    /**
     * 
     * 
     * @param vArgArray
     */
    public void setArg(
            final com.deepsky.lang.plsql.castor.mapping.Arg[] vArgArray) {
        //-- copy array
        _argList.clear();
        
        for (int i = 0; i < vArgArray.length; i++) {
                this._argList.add(vArgArray[i]);
        }
    }

    /**
     * Sets the value of field 'desc'.
     * 
     * @param desc the value of field 'desc'.
     */
    public void setDesc(
            final java.lang.String desc) {
        this._desc = desc;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'returnType'.
     * 
     * @param returnType the value of field 'returnType'.
     */
    public void setReturnType(
            final java.lang.String returnType) {
        this._returnType = returnType;
    }

    /**
     * Sets the value of field 'validatorClass'.
     * 
     * @param validatorClass the value of field 'validatorClass'.
     */
    public void setValidatorClass(
            final java.lang.String validatorClass) {
        this._validatorClass = validatorClass;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.deepsky.lang.plsql.castor.mapping.FunctionType
     */
    public static com.deepsky.lang.plsql.castor.mapping.FunctionType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.deepsky.lang.plsql.castor.mapping.FunctionType) Unmarshaller.unmarshal(com.deepsky.lang.plsql.castor.mapping.FunctionType.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
