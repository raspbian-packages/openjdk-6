// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import java.util.logging.*;
public class PropMwmHints extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return ((XlibWrapper.dataModel == 32)?(20):(40)); }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public PropMwmHints(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public PropMwmHints() {
		log.finest("Creating");
		pData = unsafe.allocateMemory(getSize());
		should_free_memory = true;
	}


	public void dispose() {
		log.finest("Disposing");
		if (should_free_memory) {
			log.finest("freeing memory");
			unsafe.freeMemory(pData); 
	}
		}
	public long get_flags() { log.finest("");return (Native.getLong(pData+0)); }
	public void set_flags(long v) { log.finest(""); Native.putLong(pData+0, v); }
	public long get_functions() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)))); }
	public void set_functions(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)), v); }
	public long get_decorations() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(8):(16)))); }
	public void set_decorations(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(8):(16)), v); }
	public long get_inputMode() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)))); }
	public void set_inputMode(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)), v); }
	public long get_status() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)))); }
	public void set_status(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)), v); }


	String getName() {
		return "PropMwmHints"; 
	}


	String getFieldsAsString() {
		String ret="";

		ret += ""+"flags = " + get_flags() +", ";
		ret += ""+"functions = " + get_functions() +", ";
		ret += ""+"decorations = " + get_decorations() +", ";
		ret += ""+"inputMode = " + get_inputMode() +", ";
		ret += ""+"status = " + get_status() +", ";
		return ret;
	}


}


