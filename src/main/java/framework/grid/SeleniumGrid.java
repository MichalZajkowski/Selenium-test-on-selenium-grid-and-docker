package framework.grid;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.remote.server.SeleniumServer;

public class SeleniumGrid {

    private final Hub hub;
    private final GridNodeServer node;
    private final SelfRegisteringRemote remote;

    public SeleniumGrid() {
        RegistrationRequest registrationRequest = new RegistrationRequest(GridNodeConfiguration.loadFromJSON("selenium-grid-node-config.json"));
        hub = new Hub(GridHubConfiguration.loadFromJSON("selenium-grid-hub-config.json"));
        node = new SeleniumServer(registrationRequest.getConfiguration());
        remote = new SelfRegisteringRemote(registrationRequest);
    }

    public void runHub() {
        hub.start();
    }

    public void stopHub() {
        hub.stop();
    }

    public void runNode() {
        remote.setRemoteServer(node);
        remote.startRemoteServer();
        remote.sendRegistrationRequest();
        remote.startRegistrationProcess();
        remote.setTimeout(1800, 5);
    }

    public void stopNode() {
        remote.stopRemoteServer();
    }
}
