package com.retroimmersive.ultimatefantasy.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Partículas y efectos atmosféricos de la Sección 5:
 * auroras boreales, niebla mística de Bosque Encantado, vegetación
 * bioluminiscente y burbujeo del Ácido.
 */
public final class ModParticlesClient {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Particles");

	private ModParticlesClient() {}

	public static void register() {
		LOGGER.info("Registrando partículas atmosféricas (auroras, niebla mística, ácido)...");
		// TODO: ParticleFactoryRegistry.getInstance().register(...)
	}
}
