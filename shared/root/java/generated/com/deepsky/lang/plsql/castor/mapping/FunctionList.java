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
 * Class FunctionList.
 * 
 * @version $Revision$ $Date$
 */
public class FunctionList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _functionList.
     */
    private java.util.List _functionList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FunctionList() {
        super();
        this._functionList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFunction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFunction(
            final com.deepsky.lang.plsql.castor.mapping.Function vFunction)
    throws java.lang.IndexOutOfBoundsException {
        this._functionList.add(vFunction);
    }

    /**
     * 
     * 
     * @param index
     * @param vFunction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFunction(
            final int index,
            final com.deepsky.lang.plsql.castor.mapping.Function vFunction)
    throws java.lang.IndexOutOfBoundsException {
        this._functionList.add(index, vFunction);
    }

    /**
     * Method enumerateFunction.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateFunction(
    ) {
        return java.util.Collections.enumeration(this._functionList);
    }

    /**
     * Method getFunction.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.deepsky.lang.plsql.castor.mapping.Function at the given
     * index
     */
    public com.deepsky.lang.plsql.castor.mapping.Function getFunction(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._functionList.size()) {
            throw new IndexOutOfBoundsException("getFunction: Index value '" + index + "' not in range [0.." + (this._functionList.size() - 1) + "]");
        }
        
        return (com.deepsky.lang.plsql.castor.mapping.Function) _functionList.get(index);
    }

    /**
     * Method getFunction.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.deepsky.lang.plsql.castor.mapping.Function[] getFunction(
    ) {
        com.deepsky.lang.plsql.castor.mapping.Function[] array = new com.deepsky.lang.plsql.castor.mapping.Function[0];
        return (com.deepsky.lang.plsql.castor.mapping.Function[]) this._functionList.toArray(array);
    }

    /**
     * Method getFunctionCount.
     * 
     * @return the size of this collection
     */
    public int getFunctionCount(
    ) {
        return this._functionList.size();
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
     * Method iterateFunction.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateFunction(
    ) {
        return this._functionList.iterator();
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
    public void removeAllFunction(
    ) {
        this._functionList.clear();
    }

    /**
     * Method removeFunction.
     * 
     * @param vFunction
     * @return true if the object was removed from the collection.
     */
    public boolean removeFunction(
            final com.deepsky.lang.plsql.castor.mapping.Function vFunction) {
        boolean removed = _functionList.remove(vFunction);
        return removed;
    }

    /**
     * Method removeFunctionAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.deepsky.lang.plsql.castor.mapping.Function removeFunctionAt(
            final int index) {
        java.lang.Object obj = this._functionList.remove(index);
        return (com.deepsky.lang.plsql.castor.mapping.Function) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFunction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFunction(
            final int index,
            final com.deepsky.lang.plsql.castor.mapping.Function vFunction)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._functionList.size()) {
            throw new IndexOutOfBoundsException("setFunction: Index value '" + index + "' not in range [0.." + (this._functionList.size() - 1) + "]");
        }
        
        this._functionList.set(index, vFunction);
    }

    /**
     * 
     * 
     * @param vFunctionArray
     */
    public void setFunction(
            final com.deepsky.lang.plsql.castor.mapping.Function[] vFunctionArray) {
        //-- copy array
        _functionList.clear();
        
        for (int i = 0; i < vFunctionArray.length; i++) {
                this._functionList.add(vFunctionArray[i]);
        }
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
     * com.deepsky.lang.plsql.castor.mapping.FunctionList
     */
    public static com.deepsky.lang.plsql.castor.mapping.FunctionList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.deepsky.lang.plsql.castor.mapping.FunctionList) Unmarshaller.unmarshal(com.deepsky.lang.plsql.castor.mapping.FunctionList.class, reader);
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
