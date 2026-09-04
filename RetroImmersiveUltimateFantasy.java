package com.retroimmersive.ultimatefantasy;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.retroimmersive.ultimatefantasy.registry.ModBiomes;
import com.retroimmersive.ultimatefantasy.registry.ModBlocks;
import com.retroimmersive.ultimatefantasy.registry.ModEntities;
import com.retroimmersive.ultimatefantasy.registry.ModItems;
import com.retroimmersive.ultimatefantasy.registry.ModSocialSystem;
import com.retroimmersive.ultimatefantasy.registry.ModWorldGen;

/**
 * Entrypoint "main" (lógico/servidor) de Retro-Immersive Ultimate Fantasy.
 * <p>
 * IMPORTANTE (aislamiento de classloading): esta clase NO debe referenciar
 * clases de renderizado (net.minecraft.client.*) en ningún campo, estático
 * o de instancia. Todo lo relacionado a render/GUI vive exclusivamente en
 * {@link RetroImmersiveUltimateFantasyClient}, que corre en su propio
 * classloader de cliente.
 */
public class RetroImmersiveUltimateFantasy implements ModInitializer {

	public static final String MOD_ID = "retroimmersiveultimatefantasy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[Retro-Immersive Ultimate Fantasy] Inicializando contenido lógico...");

		try {
			// Sección 2/3/4/7: bloques, ítems, entidades y biomas.
			// (Cada clase de registro es un stub organizado por sección del diseño —
			// se completa incrementalmente, no bloquea el resto del mod si falla.)
			ModBlocks.register();
			ModItems.register();
			ModEntities.register();
			ModBiomes.register();

			// Sección 6: worldgen (Far Lands, Nether renovado, terreno monumental).
			ModWorldGen.register();

			// Sección 8: sistema social / afinidad / vínculo doble.
			ModSocialSystem.register();

			LOGGER.info("[Retro-Immersive Ultimate Fantasy] Contenido lógico inicializado correctamente.");
		} catch (Throwable t) {
			// No tumbamos el servidor por un módulo de contenido incompleto;
			// lo dejamos asentado en consola para depuración.
			LOGGER.error("[Retro-Immersive Ultimate Fantasy] Error inicializando contenido lógico", t);
		}
	}
}
