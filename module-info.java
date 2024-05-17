module Currency.Prices {
    requires java.net.http;
    requires com.google.gson;

    exports src.model to com.google.gson;
}