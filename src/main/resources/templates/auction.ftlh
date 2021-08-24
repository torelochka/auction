<#import "layouts/base.ftlh" as base>
<@base.main title="Аукцион" css=["slider.css"] scripts=["slider.js"]>
    <div class="container">
    <h1 class="text-center">Аукцион ${auction.title}</h1>
    <div class="slider">
        <#list auction.photosName as photo>
            <div class="item">
                <img src="/dist/img/${photo.photoName}">
            </div>
        </#list>
        <a class="prev" onclick="minusSlide()">&#10094;</a>
        <a class="next" onclick="plusSlide()">&#10095;</a>
    </div>
    <h2>Описание</h2>
    <p>${auction.description}</p>
    <h2>Финальная ставка</h2>
    <p>${auction.price}</p>
    <h2>ставки</h2>
    <div class="row">
        <ul class="list-group list-group-flush">
            <#list bets as bet>
                <li class="list-group-item">
                    <strong>${bet.user.email}</strong> ${bet.price}
                </li>
            </#list>
        </ul>
    </div>
    <#if user??>
        <#if user.id == auction.owner.id>
            <a href="/auction/${auction.id}/change" class="btn btn-primary">Изменить</a>
            <a href="/auction/${auction.id}/delete" class="btn btn-danger">Удалить</a>
        <#else>
            <form method="post" action="/auction/${auction.id}/bet">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <label for="select">Валюта ставки</label>
                <select class="form-select" id="select" aria-label="" name="currency" required>
                    <option value="RUB" selected>Рубли</option>
                    <option value="USD">Доллары</option>
                </select>
                <div class="form-group col">
                    <label for="from">Поставить ставку</label>
                    <input id="from" type="number" step="any" class="form-control" name="price" required/>
                </div>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
        </#if>
    </#if>
</@base.main>