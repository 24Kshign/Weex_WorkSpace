export default {
  methods: {
    reset (path) {
      this.$router.replace(path)
    },
    push (path) {
      var baseUrl = weex.config.bundleUrl.split('/').slice(0, -1).join('/')
      const navigator = weex.requireModule('navigator')
      if (weex.config.env.platform === 'Web') {
        this.$router.push({name: path, params: {}})
      } else {
        // let baseUrl = this.getBaseUrl();
        // let q = this.createQuery(params)
        // const modal = weex.requireModule('modal')
        // modal.toast({message: baseUrl + '/' + path + '.js', duration: 1.0})
        navigator.push({
          url: baseUrl + '/' + path + '.js',
          animated: 'true'
        }, event => {
          // modal.toast({ message: 'callback: ' + event, duration: 1.0})
        })
      }
    },
    pop () {
      if (weex.config.env.platform === 'Web') {
        this.$router.go(-1)
      }
    },
    post (param, result) {
      // storage.getItem('user', function (e) {
      //   var user = e.data !== 'undefined' ? JSON.parse(e.data) : {}
      // })
      // const modal = weex.requireModule('modal')
      const stream = weex.requireModule('stream')
      var domain = 'https://wms.haimaiche.com/'
      if (weex.config.env.platform === 'Web') {
        domain = '/api/'
      }
      stream.fetch({
        method: 'POST',
        url: domain + param.path,
        type: 'json',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(param.params)
      }, ret => {
        // this作用域问题, function(ret) 要在外 vat that = this，在函数内用that.xxx
        // modal.toast({message: JSON.stringify(ret, null, 4), duration: 1.0})
        if (ret.ok && ret.data && ret.data.success === true) {
          result(ret.data)
        } else {
          var msg = ret.data.message
          msg = typeof msg === 'string' ? msg : (typeof ret.data === 'string' ? ret.data : '请求失败')
          result({
            data: null,
            success: false,
            code: ret.data.code ? ret.data.code : ret.status,
            message: msg
          })
        }
      }, progeress => {
        // modal.toast({message: JSON.stringify(process), duration: 1.0})
      })
    },
    get (param, result) {
      const stream = weex.requireModule('stream')
      const domain = '/api/'
      stream.fetch({
        method: 'GET',
        url: domain + param.path,
        type: 'json',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(param.params)
      }, ret => {
        // this作用域问题, function(ret) 要在外 vat that = this，在函数内用that.xxx
        // modal.toast({message: JSON.stringify(ret), duration: 1.0})
        var data = {}
        if (ret.ok && ret.data && ret.data.success === true) {
          data.result = ret.data
          data.success = true
          data.code = 1
          data.message = null
        } else {
          data.result = null
          data.success = false
          data.code = 3
          data.message = ret.data.message ? ret.data.message : ret.data
        }
        result(data)
      }, progeress => {
        // modal.toast({message: JSON.stringify(process), duration: 1.0})
      })
    }
  }
}
