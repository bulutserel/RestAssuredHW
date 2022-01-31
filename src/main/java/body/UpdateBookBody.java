package body;

public class UpdateBookBody {
    public static String updateBody(){
        return "{\n" +
                "    \"firstname\" : \"Bulut\",\n" +
                "    \"lastname\" : \"Serel\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
    }
}
