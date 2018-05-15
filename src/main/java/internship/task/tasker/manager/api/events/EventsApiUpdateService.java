package internship.task.tasker.manager.api.events;

import internship.task.tasker.Interfaces.EventsApiUpdateInterface;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class EventsApiUpdateService implements EventsApiUpdateInterface {
    @Autowired
    private Environment environment;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate = new RestTemplate();

    public void doUpdateForSession(Integer id, String updateType, String value) {
        logger.info("Trying to do update for Session ");
        try {
            restTemplate.getForObject(environment.getProperty("update_session") + id + "&updateType=" + updateType + "&value=" + value, SessionModel.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForSession", ex);
            logger.warn(ex.getMessage());
        }
    }

    public void doUpdateForSpeaker(Integer id, String updateType, String value) {
        logger.info("Trying to do update for Speaker");
        try {
            restTemplate.getForObject(environment.getProperty("update_speaker") + id + "&updateType=" + updateType + "&value=" + value, SpeakerModel.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForSpeaker", ex);
            logger.warn(ex.getMessage());
        }
    }

    public void doUpdateForContext(String id, String state) {
        try {
            restTemplate.getForObject(environment.getProperty("update_context") + id + "&state=" + state, ContextModel.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForContext", ex);
            logger.warn(ex.getMessage());
        }
    }


}
