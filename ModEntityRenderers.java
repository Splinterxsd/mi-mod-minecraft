package com.retroimmersive.ultimatefantasy.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registro de EntityRenderers para la fauna real y las criaturas míticas
 * (Secciones 3 y 4). Se registra únicamente dentro de {@code register()},
 * nunca en campos estáticos de esta clase — cumple el aislamiento de
 * classloading exigido para {@code onInitializeClient()}.
 */
public final class ModEntityRenderers {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Renderers");

	private ModEntityRenderers() {}

	public static void register() {
		LOGGER.info("Registrando renderers de entidades (fauna real, unicornios, grifos, fénix, dragones, ents, golems)...");
		// TODO: EntityRendererRegistry.register(ModEntities.X, ContextX::new)
	}
}
