package utilities.internal;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ToStringStyle;

import domain.DomainEntity;

public class CustomPrintStyle extends ToStringStyle {
	
	private static final long serialVersionUID = -7153302077570861674L;
	
	CustomPrintStyle() {
        super();        
        this.setUseShortClassName(false);
        this.setUseIdentityHashCode(false);  
        this.setArraySeparator(", ");
        this.setContentStart("{");
        this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "\t");
        this.setFieldSeparatorAtStart(true);
        this.setContentEnd(SystemUtils.LINE_SEPARATOR + "}");
        this.setArrayContentDetail(true);
        this.setDefaultFullDetail(true);
    }
	
	protected void appendObject(StringBuffer buffer, Object value) {
		appendDetail(buffer, null, value);
	}

	@Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
		String left, right;
		
		if (value instanceof String)
			left = right = "\"";
		else if (value instanceof Character)
			left = right = "\'";
		else if (!(value instanceof DomainEntity) && !(value instanceof Number)) {
			left = "<<";
			right = ">>";
		} else {
			left = "";
			right = "";
		}
		
		buffer.append(left);
        buffer.append(value);
        buffer.append(right);
    }
	
    private Object readResolve() {
        return this.getClass().getName();
    }	
}
