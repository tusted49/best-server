package io.tusted.bestserver.usecases;


import static com.googlecode.jpingy.Ping.*;

import com.googlecode.jpingy.Ping.Backend;
import com.googlecode.jpingy.PingArguments;
import com.googlecode.jpingy.PingResult;
import io.tusted.bestserver.domains.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PingServer {

  private PingArguments pingArguments;

  public String execute(final Server server) {

    try {
      pingArguments = new PingArguments.Builder().url(server.getIpAddress()).count(4).bytes(32).build();
      PingResult pingResult = ping(pingArguments, Backend.UNIX);
      return String.valueOf(pingResult.rtt_avg());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

}
