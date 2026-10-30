package dev.thillainirmal.shopops.ai;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean; import org.springframework.stereotype.Component;
@Component @ConditionalOnMissingBean(CommerceAssistant.class)
public class DisabledCommerceAssistant implements CommerceAssistant { public String answer(String conversationId,String message){throw new IllegalStateException("AI assistant is disabled. Set SHOP_AI_ENABLED=true and OPENAI_API_KEY.");} }
