<#import "layouts/base.ftlh" as base>
<@base.main title="Profile">
    <main>
        <div class="container">
            <h1>Профиль</h1>
            <h2>Выигранные аукционы</h2>
            <#list auctionsWon as auction>
                <div class="col mt-3 mb-3">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title">${auction.title}</h5>
                            <p class="card-text">${auction.date}</p>
                            <a href="/auction/${auction.id}" class="btn btn-primary">подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
            <h2>Ваши аукционы</h2>
            <#list myAuctions as auction>
                <div class="col mt-3 mb-3">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title">${auction.title}</h5>
                            <p class="card-text">${auction.date}</p>
                            <a href="/auction/${auction.id}" class="btn btn-primary">подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
            <h2>Аукционы в которых вы участвуете</h2>
            <#list currentAuctions as auction>
                <div class="col mt-3 mb-3">
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
    </main>
</@base.main>

