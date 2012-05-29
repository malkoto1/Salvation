package org.elsys.salvation.server;

import org.elsys.salvation.client.DefenceService;
import org.elsys.salvation.client.FunctionalityManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DefenceServiceImpl extends RemoteServiceServlet implements DefenceService {

	private static Defences defences;
	
	@Override
	public void saveDefences(FunctionalityManager fm) {
		defences = new Defences(fm);
	}

	@Override
	public FunctionalityManager getHardDefences(FunctionalityManager fm) {
		fm.setHardDefences(defences.getHardDefences());
		return fm;
	}

	@Override
	public FunctionalityManager getNetDefences(FunctionalityManager fm) {
		fm.setNetDefences(defences.getNetDefences());
		return fm;
	}

	@Override
	public FunctionalityManager getSoftDefences(FunctionalityManager fm) {
		fm.setSoftDefences(defences.getSoftDefences());
		return fm;
	}

}
