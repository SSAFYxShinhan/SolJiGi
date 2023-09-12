$(function() {
    (function(name) {
        var container = $('#pagination-' + name);
        if (!container.length) return;
        var sources = function () {
            var result = [];

            for (var i = 1; i < 2; i++) {
                result.push({
                    id: i,
                    nameData: "test"
                });
            }

            return result;
        }();

        var options = {
            dataSource: sources,
            callback: function (response, pagination) {
                window.console && console.log(response, pagination);

                var dataHtml = '<table class="table table-bordered overflow-auto" id="dataTable" width="100%"cellSpacing="0">';

                $.each(response, function (index, item) {
                    dataHtml += '<tbody>';
                    dataHtml += '<tr>';
                    dataHtml += '<th>' + item.id + '</th>';
                    dataHtml += '<th>' + item.nameData + '</th>';
                    dataHtml += '</tr>';
                    dataHtml += '</tbody>';
                });

                dataHtml += '</table>';
                container.prev().html(dataHtml);
            }
        };

        //$.pagination(container, options);

        container.addHook('beforeInit', function () {
            window.console && console.log('beforeInit...');
        });
        container.pagination(options);

        container.addHook('beforePageOnClick', function () {
            window.console && console.log('beforePageOnClick...');
            //return false
        });
    })('demo1');

})