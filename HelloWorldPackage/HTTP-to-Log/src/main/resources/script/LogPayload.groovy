import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap

def Message processData(Message message) {
    def body = message.getBody(String.class)
    def headers = message.getHeaders()
    def correlationId = headers.get("correlationId") ?: "N/A"

    def logger = message.getHeaders().get("SAP_ApplicationID") ?: "HTTP-to-Log"
    def auditLog = message.getExchange().getContext()
        .getRegistry().lookupByName("auditLogger")

    // Log payload and correlation ID to audit log
    message.setHeader("X-Logged-By", "HTTP-to-Log IFlow")
    message.setProperty("loggedPayloadLength", body?.length() ?: 0)

    return message
}
