package cz.tslavik.kudos.controller;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.azure.spring.data.cosmosdb.core.query.DocumentDbPageRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import cz.tslavik.kudos.entity.Kudos;
import cz.tslavik.kudos.repository.cosmos.*;

@RestController
@RequestMapping("/api/${api.version}/kudos")
public class KudosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KudosController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KudosRepository KudosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TelemetryClient telemetryClient;


    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity<Kudos> getByKudosId(@PathVariable String id) {

        telemetryClient.trackEvent("Request called");
        Kudos Kudos =KudosRepository.getKudosById(id).stream().findFirst().orElse(null);
        return new ResponseEntity<Kudos>(Kudos,HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> createKudos(@RequestBody Kudos Kudos) {

        //checkRoleDelivery(updateReadLayer(user,userRole,false).getStatusCode(),user, userRole);
        KudosRepository.save(Kudos);
        telemetryClient.trackEvent("Kudos created");
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<Void> removeKudos(@RequestBody Kudos Kudos) {

        //KudosRepository.deleteById(Kudos.getId()); you have to implement by yourself
        KudosRepository.delete(Kudos);
        telemetryClient.trackEvent("Kudos removed");
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Kudos>> getAll(Pageable pageable,@RequestParam(required = false) String cosmosToken) {
        LOGGER.debug("getUsers {}", pageable);
        return new ResponseEntity<Page<Kudos>>(KudosRepository.findAll(pageable),HttpStatus.OK);
    }
}
