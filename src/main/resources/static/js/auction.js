let page = 1;

function getMore(count) {
    $.ajax({
        url: "/more/auction?size=4&page=" + page++,
        type: "GET",
        dataType: "json",
        success: function (data) {
            data.forEach(function (auction) {
                $("#auction_block").append('<div class="col">' +
                    '                        <div class="card" style="width: 18rem;">' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + auction['title'] + '</h5>' +
                    '                                <p class="card-text">' + auction['date'] + '</p>' +
                    '                                <a href="/auction/' + auction['id'] + '" class="btn btn-primary">подробнее</a>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>')
            })
        }
    });

    if (page === count) {
        $("#getMoreButton").hide()
    }
}

if ($("#count").val() <= page) {
    $("#getMoreButton").hide()
}
