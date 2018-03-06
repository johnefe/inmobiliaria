package com.proinsalud.sistemas.web.application.component;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * @author Andres Santacruz
 * @datetime 19/02/2018 - 8:00:51 a. m.
 *
 */
public class JSFPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 7303231844048741949L;

	public void beforePhase(PhaseEvent pe) {
		// if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
		// System.out.println("Procesando una nueva peticion!");
		// }
		// System.out.println("antes de - " + pe.getPhaseId().toString());

	}

	public void afterPhase(PhaseEvent pe) {
		// if (pe.getPhaseId() == PhaseId.RENDER_RESPONSE) {
		// System.out.println("Peticion terminada!");
		// }
		// System.out.println("despues de - " + pe.getPhaseId().toString());
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
