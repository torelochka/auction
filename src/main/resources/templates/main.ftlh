<#import "layouts/base.ftlh" as base>
<@base.main title="Главная" scripts=["main.js", "auction.js"]>
    <main>
        <div class="container">
            <div class="row" id="auction_block">
                <#list auctions as auction>
                    <div class="col">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${auction.title}</h5>
                                <p class="card-text">${auction.date}</p>
                                <a href="/auction/${auction.id}" class="btn btn-primary">подробнее</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <button id="getMoreButton" onclick="getMore(${count})" class="btn btn-primary">Показать еще</button>

        </div>
    </main>
</@base.main>

