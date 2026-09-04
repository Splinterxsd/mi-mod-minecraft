package com.retroimmersive.ultimatefantasy;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.retroimmersive.ultimatefantasy.client.ModEntityRenderers;
import com.retroimmersive.ultimatefantasy.client.ModParticlesClient;
import com.retroimmersive.ultimatefantasy.client.gui.CompendioArcanoScreen;

/**
 * Entrypoint "client" de Retro-Immersive Ultimate Fantasy.
 * <p>
 * Reglas de aislamiento de classloading (obligatorias):
 * 1) NINGÚN campo {@code static final} de esta clase (ni de las clases que
 *    importa a nivel de campo estático) instancia elementos de renderizado
 *    (texturas, modelos, pantallas, renderers) fuera de {@link #onInitializeClient()}.
 *    Todo eso se hace de forma perezosa, dentro del método, cuando el
 *    classloader de cliente ya está completamente inicializado.
 * 2) Todo el cuerpo de {@link #onInitializeClient()} está envuelto en un
 *    try-catch de {@link Throwable} para que un fallo de un módulo visual
 *    (p. ej. el Compendio del Explorador Arcano) no impida que el cliente
 *    arranque; el error queda registrado en consola.
 */
public class RetroImmersiveUltimateFantasyClient implements ClientModInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasyClient");

	@Override
	public void onInitializeClient() {
		try {
			LOGGER.info("[Retro-Immersive Ultimate Fantasy] Inicializando contenido de cliente...");

			// Sección 3/4: renderers de fauna real y criaturas míticas.
			ModEntityRenderers.register();

			// Sección 5: partículas atmosféricas (auroras, niebla mística, ácido).
			ModParticlesClient.register();

			// Sección 9: registro perezoso de la pantalla del Compendio del
			// Explorador Arcano — NO se instancia acá, solo se registra el
			// proveedor de pantalla para cuando el ítem se abra.
			CompendioArcanoScreen.registerHandler();

			LOGGER.info("[Retro-Immersive Ultimate Fantasy] Contenido de cliente inicializado correctamente.");
		} catch (Throwable t) {
			LOGGER.error("[Retro-Immersive Ultimate Fantasy] Error inicializando contenido de cliente", t);
		}
	}
}
