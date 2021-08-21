<#import "layouts/base.ftlh" as base>
<@base.main title="Создать аукцион" scripts=["autoComplete.js", "flatpickr.js" ,"main.js"] css=["flatpickr.min.css"]>
    <main>
        <div class="container">
            <h3 id="tripsBlock"></h3>
            <form action="/auction" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group col">
                    <label for="from">Название</label>
                    <input id="from" class="form-control" name="title" required/>
                </div>
                <div class="form-group col">
                    <label for="from">Финальная ставка</label>
                    <input id="from"  type="number" class="form-control" name="price" step="any" required/>
                </div>
                <div class="form-group col">
                    <label for="from">Описание</label>
                    <textarea id="from" class="form-control" name="description" required></textarea>
                </div>
                <div class="form-group">
                    <label>Фото
                        <input name="photos" type="file" accept="image/*" multiple required>
                    </label>
                </div>
                <div class="check-date">
                    <label for="date-in">Дата окончания:</label>
                    <input type="text" class="date-input" id="date-in" name="date" required>
                    <i class="far fa-calendar"></i>
                </div>
                <button type="submit" class="btn btn-primary">Создать</button>
            </form>
        </div>
    </main>
</@base.main>

