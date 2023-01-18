// Chart.register(ChartDataLabels);
export const MONTHS = [
    "Tháng 1",
    "Tháng 2",
    "Tháng 3",
    "Tháng 4",
    "Tháng 6",
    "Tháng 6",
    "Tháng 7",
    "Tháng 8",
    "Tháng 9",
    "Tháng 10",
    "Tháng 11",
    "Tháng 12",
];

const labels = MONTHS.slice(0, 12);

const allCanvas = document.querySelectorAll("canvas");

allCanvas.forEach((el) => {
    const {type, colorDatalabels} = el.dataset;

    const data = {
        labels: labels,
        datasets: [
            {
                label: "Biểu đồ thống kê bài đăng năm 2021",
                backgroundColor: "rgb(252, 7, 3)",
                borderColor: "rgb(252, 7, 3)",
                data: [15, 20, 35, 36, 25, 50, 70, 60, 63, 74, 85, 110],
                tension: 0.2,
            }, {
                label: "Biểu đồ thống kê bài đăng năm 2022",
                backgroundColor: "rgb(3,31,111)",
                borderColor: "rgb(3,31,111)",
                data: [5, 25, 36, 18, 49, 70, 59, 66, 58, 84, 82, 101],
                tension: 0.2,
            }
        ],
    };

    const config = {
        type: el.dataset.type,
        data: data,
        options: {
            aspectRatio: 5/4,
            layout: {
                padding: 2,
            },
            plugins: {
                tooltip: {
                    titleFont: {
                        size: 25,
                    },
                    bodyFont: {
                        size: 20,
                    },
                    callbacks: {
                        label: (context) => `Tổng: ${context.formattedValue} bài đăng`,
                    },
                },
                datalabels: {
                    color: colorDatalabels,
                    font: {size: 20},
                    formatter: function (value, context) {
                        return value;
                    },
                    display: function (context) {
                        if (type === "line")
                            return (
                                context.dataIndex > 0 && context.dataIndex < labels.length - 1
                            );

                        return true;
                    },
                },
            },
        },
    };

    new Chart(el, config);
});
