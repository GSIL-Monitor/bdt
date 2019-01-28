<template>
  <div>
    <div style="padding-bottom: 16px">
      <Button type="info" @click="updateData">{{`导出数据`}}</Button>
    </div>
    <example prop-type="1" prop-name="ljzjz"></example>
  </div>
</template>

<script>
  import Example from './example.vue'

  export default {
    name: "c_lz",
    data() {
      return {}
    },
    components: {
      Example
    },
    mounted() {

    },
    methods: {
      updateData() {
        console.log();
        this.exportExcel();
      },
      getWeek(i) {
        let now = new Date();
        let getDay = now.getDay();
        if (now.getDay() == 0) {
          getDay = 7;
        }
        let firstDay = new Date(now - (getDay) * 86400000);
        console.log(now, firstDay.getTime(), getDay, (now.getDay() - 1) * 86400000);
        firstDay.setDate(firstDay.getDate() + i);
        let mon = Number(firstDay.getMonth()) + 1;
        mon = mon < 10 ? ('0' + mon) : mon;
        let d = firstDay.getDate()
        d = d < 10 ? ('0' + d) : d;
        // return `${now.getFullYear()}-${mon}-${d}`;
        return new Date(`${now.getFullYear()}-${mon}-${d} 00:00:00`).getTime();
      },
      exportExcel() {
        let params = {
          startTime: this.getWeek(1),
          endTime: this.getWeek(7),
          type: 3
        };
        this.$api.getAllUploadFile(params).then(res => {
          console.log(res);
          if (res.data.returnCode == 200) {
            let data = res.data.returnObject.map(e => {
              return e.id
            });
            if (data.length == 0) {
              this.$Message.success({content: '没有下载数据', duration: 10, closable: true});
              return false
            }
            console.log(data);
            this.downZip(data.toString());
          }
        }).catch(err => {

        })
        // this.$refs.tables.exportCsv({
        //   filename: `table-${(new Date()).valueOf()}.csv`
        // })
      },
      downZip(id) {
        let params = Object.assign({}, {params: {fileIds: id}}, {
          onDownloadProgress: progressEvent => {
            var complete = (progressEvent.loaded / progressEvent.total * 100 | 0);
            console.log(progressEvent, complete);
          }
        })
        this.$api.downZip(params).then(response => {
          console.log(response);
          var headers = response.headers;
          console.log(headers);
          // var filename = headers['content-disposition'].split(';')[1].split('=')[1];
          var contentType = headers['content-type'];
          var linkElement = document.createElement('a');
          try {
            console.log(12312)
            var blob = new Blob([response.data], {
              type: contentType
            });
            var url = window.URL.createObjectURL(blob);
            linkElement.setAttribute('href', url);
            linkElement.setAttribute("download", `图形表格数据${this.getWeek(1)}-${this.getWeek(7)}.zip`);
            if (typeof (MouseEvent) == 'function') {
              var event = new MouseEvent("click", {
                "view": window,
                "bubbles": true,
                "cancelable": false
              });
              linkElement.dispatchEvent(event);
              console.log('MouseEvent')
            } else if (navigator.appVersion.toString().indexOf('.NET') > 0) {
              window.navigator.msSaveBlob(blob, `图形表格数据${this.getWeek(1)}-${this.getWeek(7)}.zip`);
              console.log('NET')
            }
          } catch (err) {
            console.log(err);
          }
        }).catch(err => {

        })
      }
    }
  }
</script>

<style scoped>

</style>
