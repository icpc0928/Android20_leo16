package tw.org.iii.leo.leo16;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

// 基本型別不能泛型 所以用byte[]就是物件ＯＫ
public class LeoInputStreamRequest extends Request<byte[]> {
    private Response.Listener<byte[]> listener;
    private Map<String,String> responseHeader;
    private Map<String,String> params;

    public LeoInputStreamRequest(
            int method,
            String url,
            Map<String,String> params ,
            Response.Listener<byte[]> listener,
            @Nullable Response.ErrorListener errorListener
            ) {
        super(method, url, errorListener);
        this.listener = listener;
        this.params = params;

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        responseHeader = response.headers;
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);

    }
}
