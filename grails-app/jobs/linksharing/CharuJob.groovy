package linksharing


class CharuJob {

    static triggers = {
        //simple name: 'mySimpleTrigger', startDelay: 10000, repeatInterval: 1000
        //       cron name: 'myTrigger', cronExpression: "0/2 * * * * ?"


    }
    def timeout = 1000l // execute job once in 5 seconds

    def execute() {
        println "hiiiiiee>>>>>>>>>>>"
    }
}
