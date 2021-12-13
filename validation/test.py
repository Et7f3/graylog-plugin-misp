# to create and populate the test venv:
# * python3 -m venv venv
# * source venv/bin/activate
# * pip install -r requirements.txt 
# to execute these tests:
# * activate venv
#   source ./venv/bin/activate
# * execute tests
#   python -m unittest

from unittest import TestCase
from graylog_server import GraylogServer
from graylog_rest_api import GraylogRestApi

_PERIOD = 5


class Test(TestCase):

    def setUp(self) -> None:
        # TODO maybe merge _graylog and _graylog_rest_api
        self._graylog = GraylogServer('../runtime')
        self._graylog.start()
        self._graylog_rest_api = GraylogRestApi()
        self._graylog_rest_api.wait_until_graylog_has_started()

    def tearDown(self) -> None:
        self._graylog.stop()

    def test_start_should_load_plugin(self):
        logs = self._graylog.extract_logs()
        self.assertIn('INFO : org.graylog2.bootstrap.CmdLineTool - Loaded plugin: MISP', logs)

    def _has_pipeline_rule_function(self, name):
        pipeline_rule_functions = self._graylog_rest_api.get('system/pipelines/rule/functions')
        for pipeline_rule_function in pipeline_rule_functions.json():
            if pipeline_rule_function['name'] == name:
                return True
        return False

    def test_pipeline_rule_functions_should_include_misp_lookup(self):
        self.assertTrue(self._has_pipeline_rule_function('misp_lookup'))
