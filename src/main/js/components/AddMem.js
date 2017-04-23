import React from 'react';
import {Grid,Row,Col} from 'react-bootstrap';
import {Form,FormGroup,ControlLabel,FormControl,Button} from 'react-bootstrap';

export default class MainPage extends React.Component{
    constructor(props){
        super(props);
    }

    componentDidMount(){

    }

    addMem(){

    }

    render(){
        return(<Row className="mem">
            <Col sm={1} md={2} />
            <Col sm={10} md={8} >
                <h2>Add mem:</h2>
                <hr />
                <Form>
                    <FormGroup bsSize="small">
                        <h4><ControlLabel>Mem title:</ControlLabel></h4>
                        <FormControl type="text" id="title" inputRef={(ref) => {this.title = ref}} placeholder="Title"/>
                    </FormGroup>
                    <FormGroup bsSize="small">
                        <h4><ControlLabel>Image file:</ControlLabel></h4>
                        <FormControl type="file" id="file" inputRef={(ref) => {this.file = ref}}/>
                    </FormGroup>
                    <Button bsStyle="warning" bsSize="small" onClick={this.addMem.bind(this)} block>
                        Add mem
                    </Button>
                </Form>
            </Col>
            <Col sm={1} md={2} />
        </Row>);
    }
}
