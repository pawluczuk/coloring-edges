(function(d3, $, _) {
    function enableD3JS(fileName, selector, response) {
        "use strict";

        var colors = response.edgeWithMaxColor.color;
        var nodesNo = response.vertices.length;

        var width = 1200,
            height = 550;

        var force = d3.layout.force()
            .charge(-200)
            .linkDistance(900/nodesNo)
            .size([width, height])
            .linkStrength(0.1)
            .friction(0.9)
            .gravity(0.1)
            .theta(0.8)
            .alpha(0.1)
            .start();

        selector = selector || '#picbox';
        var svg = d3.select(selector).append("svg")
            .attr("width", width)
            .attr("height", height);

        var tip = d3.tip()
            .attr('class', 'd3-tip')
            .offset([-10, 0])
            .html(function(d) {
                return "<strong>Node:</strong> <span style='color:red'>" + d.id + "</span>";
            });

        var tip1 = d3.tip()
            .attr('class', 'd3-tip')
            .offset([0, 0])
            .html(function(d) {
                return "<strong>Color:</strong> <span style='color:red'>" + d.color + "</span>";
            });

        var colorScale = function(colorsNo) {
            if (colorsNo <= 10)
                return d3.scale.category10();
            else if (colorsNo <= 20)
                return d3.scale.category20();
            else
            // fix colors
                return d3.scale.linear()
                    .domain([1,colorsNo])
                    .range(['#ECECEC', 'black']);
        };

        svg.call(tip);
        svg.call(tip1);

        d3.csv(fileName, function(d) {
            return {
                source: d.SourceVertex,
                target: d.DestinationVertex,
                color: d.Color
            }
        }, function(error, graph) {
            if (error) throw error;

            var uni = [];
            for (i = 0; i < graph.length; i++) {
                uni = _.union(uni, [graph[i].source], [graph[i].target]);
            }

            var nodes = [];
            for(i = 0; i < uni.length; i++) {
                nodes.push({id: uni[i]});
            }

            for (var i = 0; i < graph.length; i++) {
                graph[i].source = translateToIndex(nodes, graph[i].source);
                graph[i].target = translateToIndex(nodes, graph[i].target);
            }
            var color = colorScale(colors);

            force
                .nodes(nodes)
                .links(graph)
                .start();

            var link = svg.selectAll(".link")
                .data(graph)
                .enter().append("line")
                .attr("class", "link")
                .style("stroke-width", function(d) { return Math.sqrt(d.value); });

            var node = svg.selectAll(".node")
                .data(nodes)
                .enter().append("circle")
                .attr('r', 5)
                .attr("class", "node")
                .call(force.drag);

            node.append("title")
                .text(function(d) { return d.id; });

            force.on("tick", function() {
                link.attr("x1", function(d) { return d.source.x; })
                    .attr("y1", function(d) { return d.source.y; })
                    .attr("x2", function(d) { return d.target.x; })
                    .attr("y2", function(d) { return d.target.y; })
                    .style("stroke", function(d) { return color(d.color); })
                    .on('mouseover', tip1.show)
                    .on('mouseout', tip1.hide);

                node.attr("cx", function(d) { return d.x; })
                    .attr("cy", function(d) { return d.y; })
                    .on('mouseover', tip.show)
                    .on('mouseout', tip.hide);
            });
        });
    }

    function translateToIndex(array, input) {
        "use strict";

        for (var i = 0; i < array.length; i++) {
            if (parseInt(array[i].id) === parseInt(input))
                return i;
        }
    }

    var btn = document.getElementById('upload-btn'),
        picBox = document.getElementById('picbox'),
        errBox = document.getElementById('errormsg');

    var uploader = new ss.SimpleUpload({
        button: btn,
        url: 'upload',
        name: 'file',
        multiple: true,
        multipart: true,
        maxUploads: 2,
        queue: false,
        allowedExtensions: ['txt'],
        accept: '.txt',
        debug: true,
        hoverClass: 'btn-hover',
        focusClass: 'active',
        disabledClass: 'disabled',
        responseType: 'json',
        onExtError: function () {
            errBox.innerHTML = 'Invalid file type. Please select a TXT file.';
        },
        onComplete: function (file, response) {
            if (!response) {
                errBox.innerHTML = 'Unable to upload file';
            } else {
                picBox.innerHTML = '<div class="col-md-12">' +
                    '<a class="btn btn-warning" href="/output" role="button">Download</a>' +
                    'Number of vertices: ' + response.vertices.length +
                    ' Number of edges: ' + response.edges.length +
                    ' Number of colors: ' + response.edgeWithMaxColor.color +
                    ' Delta(G): ' + response.deltaGraph + '</div>';
                 enableD3JS('upload-dir/output.csv', '#picbox', response);
            }
        }
    });
})(d3, $, _);