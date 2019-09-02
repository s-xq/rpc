package com.sxq.rpc.protocol.invoke;

import java.util.Arrays;

/**
 * Created by s-xq on 2019-09-01.
 */

public class Invocation {

    private String interfaceCls;

    private String methodName;

    private String[] parameterTypes;

    private Object[] args;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInterfaceCls() {
        return interfaceCls;
    }

    public void setInterfaceCls(String interfaceCls) {
        this.interfaceCls = interfaceCls;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"interfaceCls\":")
                .append(interfaceCls);
        sb.append(",\"methodName\":\"")
                .append(methodName).append('\"');
        sb.append(",\"parameterTypes\":")
                .append(Arrays.toString(parameterTypes));
        sb.append(",\"args\":")
                .append(Arrays.toString(args));
        sb.append('}');
        return sb.toString();
    }
}
