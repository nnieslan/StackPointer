/*
 * Make sure you include StackExchange's API and jQuery along with this file!
 */

function stackInit() {
    SE.init({
        clientId: 676,
        key: 'ubwVxucHGeVndxd5knjnMg((',
        channelUrl: 'http://localhost:8084',
        complete: function (data) { $("#vers").text("Response from SX init: "+data.version); }
    });
}

function getQuestions() {
    //https://api.stackexchange.com/2.1/questions?page=1&pagesize=100&order=desc&sort=creation&site=stackoverflow
}