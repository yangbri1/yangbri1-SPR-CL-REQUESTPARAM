package Application.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    /**
     * Query parameters allow the developer in include some variable data in an HTTP request, as part of the endpoint,
     * that may be used to conduct some very specific query or action on your backend. A query parameter is the part
     * of the URL following the ? symbol, such as cats.site/cats?term=photos&format=png&orderby=popularity. For instance,
     * if you are writing an endpoint intended to return some search results, it would be conventional to include not
     * just the search term (eg 'cat photos') as a query parameter, but other site metrics such as filters
     * ('format=png'), ordering ('orderby=popularity'), or pagination ('page=2'). In a RESTful API, it is generally
     * recommended that a path param only identifies the entity or resource that you are working with, and variables
     * that refine how the backend should interact with the data should be included as a path parameter.
     *
     * Below is an example of how to extract a path parameter from an HTTP request.
     * This will extract the query parameter "term" from an HTTP request to localhost:9000/cats?term=searchterm,
     * responding with "searchterm".
     */
    @GetMapping(value = "cats", params = {"term"})
    public String getSearchTerm(@RequestParam String term){
        return term;
    }
    /**
     * Here's an example of how to extract multiple path params from the HTTP quest. Notice how the terms are defined
     * as part of the endpoint's signature - this allows Spring to identify two distinct endpoints that both use a
     * base endpoint "cats" but feature different query parameters.
     */
    @GetMapping(value = "cats", params = {"term", "format"})
    public String[] getSearchTermAndPage(@RequestParam String term, @RequestParam String format){
        return new String[]{term, format};  // String ary of size 2 -- w/ 'term' & 'format' query params
    }
    /**
     * TODO: extract the numeric 'amount' query parameter from a request, such as GET localhost:9000/cats?amount=50,
     * returning 50.
     */
    @GetMapping(value = "cats", params = {"amount"})
    // IMPORTANT: include '@RequestParam String amount' --- wanted query parameters as params to @GetMapping's method otw Java views 'amount' as a variable unknowingly
    public int getSearchFormat(@RequestParam int amount){  
        /* 1st way ... this may be safer in other languages --- i.e., JavaScript where query params are viewed by default in String datatypes ...
        so they will be extracted & returned as String too --- despite it could be viewed as number, boolean, etc. */  
        
        // both type-cast retrieved 'amount' query params from String into 'int' value
        // return Integer.parseInt(amount);  
        // return Integer.valueOf(amount);
        // return (int) amount;    // explicit type-casting from String to int DN seem to work in Java

        /* 2nd way ... */
        /* INTERESTING!! --- Unlike in JavaScript where query params are of String type by default ---
         * here if initialize query param to a certain data type in params above to 'int' in .getSearchFormat(@RequestParm int amount) ...
         * we can just return 'amount' by itself as this method is looking for 'amount' in as an 'int'
        */
        return amount;             
    }
    /**
     * TODO: extract the String 'format' and 'orderBy' query parameters from a request, such as
     * GET localhost:9000/cats?format=gif&orderby=new, returning a String array such as {"gif", "new"}
     */
    @GetMapping(value = "cats", params = {"format", "orderBy"})
    public String[] getSearchFormatAndAmount(@RequestParam String format, @RequestParam String orderBy){     // Notice: return type of String[] ary
        String[] queryStrAry = new String[]{format, orderBy};
        return queryStrAry;
    }
}
