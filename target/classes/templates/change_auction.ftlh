<#import "layouts/base.ftlh" as base>
<@base.main title="Изменить аукцион" scripts=["flatpickr.js" ,"main.js"] css=["flatpickr.min.css"]>
    <main>
        <div class="container">
            <h3 id="tripsBlock"></h3>
            <form action="/auction/${auction.id}/change" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group col">
                    <label for="from">Название</label>
                    <input id="from" class="form-control" name="title" required value="${auction.title}"/>
                </div>
                <div class="form-group col">
                    <label for="from">Финальная ставка</label>
                    <input id="from"  type="number" class="form-control" name="price" step="any" required/>
                </div>
                <div class="form-group col">
                    <label for="from">Описание</label>
                    <textarea id="from" class="form-control" name="description" required>${auction.description}</textarea>
                </div>
                <div class="check-date">
                    <label for="date-in">Дата окончания:</label>
                    <input type="text" class="date-input" id="date-in" name="date" required>
                    <i class="far fa-calendar"></i>
                </div>
                <button type="submit" class="btn btn-primary">Изменить</button>
            </form>
        </div>
    </main>
</@base.main>

