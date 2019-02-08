//package cz.tslavik.Kudosservice.repository.cosmos;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.microsoft.azure.documentdb.*;
//import cz.tslavik.Kudosservice.entity.Kudos;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class KudosRepositoryImpl implements KudosRepositoryCustom{
//
//    private final String databaseId = "UsersDB";
//    private final String collectionId = "users";
//    private final String partitionKeyFieldName = "departmentname";
//    private final String partitionKeyPath = "/" + partitionKeyFieldName;
//    private final String collectionLink = String.format("/dbs/%s/colls/%s", databaseId, collectionId);
//
//    private DocumentClient client = new DocumentClient("https://inca-test.documents.azure.com:443/",
//            "DybchxSEORoEV4HWhxlo9cDUSIx3jQIbEmW7iSZ1SlWtGA0Ev0jrEfNAMzIb4iG0Uuhv3YtOoLnYhZTRvJA7jA==",
//            ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
//
//
//    @Override
//    public Page<Kudos> getKudosByState(String state, Pageable pageable) {
//        FeedOptions options = new FeedOptions();
//        options.setEnableCrossPartitionQuery(true);
//
//        String query = null;
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//        for (String role: state)   {
//            count++;
//            query = sb.append(count>1?" and ":"").append("ARRAY_CONTAINS(a.role,"+role+")").toString();
//
//        }
//
//        List<Document> it = client.queryDocuments(collectionLink,
//                "SELECT c FROM c join a in c.applications.roles where a.applicationid=\"1\" \n" +
//                        "and ("+query+")",
//                options).getQueryIterable().toList();
//        List<Kudos> ur = new ArrayList<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        for (Document document : it){
//            Kudos oread = null;
//            try {
//                oread = mapper.readValue(document.getObject("c").toString(), Kudos.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ur.add(oread);
//        }
//        Page<Kudos> usersPageFiltered  = new PageImpl<Kudos>(ur);
//        return usersPageFiltered;
//    }
//
//}
