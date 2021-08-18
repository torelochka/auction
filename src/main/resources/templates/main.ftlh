<#import "layouts/base.ftlh" as base>
<@base.main title="Главная" scripts=["main.js"]>
    <main>
        <div class="container">
            <div class="row">
                <#list auctions as auction>
                    <div class="col">
                        <div class="card" style="width: 18rem;">
                            <img src="/dist/img/${auction.photosName.get(0).photoName}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${auction.title}</h5>
                                <p class="card-text">${auction.date}</p>
                                <a href="/auction/${auction.id}" class="btn btn-primary">подробнее</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </main>
</@base.main>

