<#import "layouts/base.ftlh" as base>
<@base.main title="Главная" scripts=["autoComplete.js", "flatpickr.js" ,"main.js"] css=["flatpickr.min.css"]>
    <main>
        <div class="container">
            <h3 id="tripsBlock"></h3>
            <form method="post" action="/trip" autocomplete="off">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-row">
                    <div class="form-group col">
                        <label for="from">From</label>
                        <input id="from" class="form-control" name="from"/>
                    </div>
                    <div class="form-group col">
                        <label for="to">To</label>
                        <input id="to" class="form-control" name="to"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Find</button>
                </div>
            </form>
            <form action="/quartz" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="check-date">
                    <label for="date-in">Заезд:</label>
                    <input type="text" class="date-input" id="date-in" name="date">
                    <i class="far fa-calendar"></i>
                </div>
                <div class="form-group col">
                    <label for="from">title</label>
                    <input id="from" class="form-control" name="title"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </main>
</@base.main>

