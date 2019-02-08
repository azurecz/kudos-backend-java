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

@RestController
@RequestMapping("/api/v1/kudos")
public class KudosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KudosController.class);

//    @Value("${azure.search.uri}")
//    private String searchUri;
//    @Value("${azure.search.keyname}")
//    private String keyname;
//    @Value("${azure.search.keypass}")
//    private String keypass;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    cz.tslavik.kudos.repository.cosmos.KudosRepository KudosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TelemetryClient telemetryClient;



    //@PreAuthorize("hasAnyRole(T(cz.csas.users.enums.UserRoles).USER.toString(), T(cz.csas.users.enums.UserRoles).DEVELOPER.toString())")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity<Kudos> getByKudosId(@PathVariable String id) {

        //Kudos KudosList = KudosRepository.findById(id).stream().findFirst().orElse(null);
      //  KudosRepository.save(Kudos.builder().id("1").createdby("2500").build());
        telemetryClient.trackEvent("Request called");
        Kudos Kudos =KudosRepository.getKudosById(id).stream().findFirst().orElse(null);
        return new ResponseEntity<Kudos>(Kudos,HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Void> createKudos(@RequestBody Kudos Kudos) {

        //checkRoleDelivery(updateReadLayer(user,userRole,false).getStatusCode(),user, userRole);
        KudosRepository.save(Kudos);
        telemetryClient.trackEvent("Kudos created");
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/remove",
            consumes = "application/json")
    public ResponseEntity<Void> removeKudos(@RequestBody Kudos Kudos) {


        //checkRoleDelivery(updateReadLayer(user,userRole,false).getStatusCode(),user, userRole);
        //KudosRepository.deleteById(Kudos.getId()); you have to implement by yourself
        KudosRepository.delete(Kudos);
        telemetryClient.trackEvent("Kudos created");
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Kudos>> getAll(Pageable pageable,@RequestParam(required = false) String cosmosToken) {
        LOGGER.debug("getUsers {}", pageable);
        return new ResponseEntity<Page<Kudos>>(KudosRepository.findAll(pageable),HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole(T(cz.csas.users.enums.UserRoles).USER.toString(), T(cz.csas.users.enums.UserRoles).DEVELOPER.toString())")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/paging")
    public ResponseEntity<Page<Kudos>> getKudoss(Pageable pageable,@RequestParam(required = false) String cosmosToken) {
        LOGGER.debug("getUsers {}", pageable);
        Page<Kudos> Kudoss = null;
//            Pageable dbPageRequest = new DocumentDbPageRequest(pageable.getPageNumber(), pageable.getPageSize(),cosmosToken);
            Pageable dbPageRequest = new DocumentDbPageRequest(1, 10,cosmosToken);
            Page<Kudos> usersPage = KudosRepository.findAll(dbPageRequest);

        return new ResponseEntity<Page<Kudos>>(Kudoss,HttpStatus.OK);

    }
}
