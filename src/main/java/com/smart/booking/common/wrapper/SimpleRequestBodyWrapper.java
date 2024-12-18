package com.smart.booking.common.wrapper;


import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleRequestBodyWrapper extends HttpServletRequestWrapper implements LoggingRequestWrapper {

    private final byte[] rowData;
    private final ByteArrayInputStream byteArrayInputStream;

    public SimpleRequestBodyWrapper(
        HttpServletRequest request
    ) throws Exception {
        super(request);
        rowData = request.getInputStream().readAllBytes();
        byteArrayInputStream = new ByteArrayInputStream(getRowByteArray());
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    private byte[] getRowByteArray() {
        return rowData;
    }

    @Override
    public String getBody() {
        return new String(getRowByteArray(), StandardCharsets.UTF_8);
    }
}
