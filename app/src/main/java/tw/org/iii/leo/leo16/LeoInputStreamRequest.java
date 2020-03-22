package tw.org.iii.leo.leo16;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

// 基本型別不能泛型 所以用byte[]就是物件ＯＫ
public class LeoInputStreamRequest extends Request<byte[]> {
    private Response.Listener<byte[]> listener;
    private Map<String,String> responseHeader;
    private Map<String,String> params;

    public LeoInputStreamRequest(
            int method,
            String url,
            Response.Listener<byte[]> listener,
            @Nullable Response.ErrorListener errorListener,
            Map<String,String> params ) {
        super(method, url, errorListener);
        this.listener = listener;
        this.params = params;

    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(byte[] response) {

    }
}
