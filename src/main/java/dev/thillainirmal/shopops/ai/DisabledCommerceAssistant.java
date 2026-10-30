package dev.thillainirmal.shopops.ai;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty; import org.springframework.stereotype.Component;
@Component @ConditionalOnProperty(name="shop.ai.enabled",havingValue="false",matchIfMissing=true)
public class DisabledCommerceAssistant implements CommerceAssistant { public String answer(String conversationId,String message){throw new IllegalStateException("AI assistant is disabled. Set SHOP_AI_ENABLED=true and OPENAI_API_KEY.");} }
