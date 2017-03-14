import React from 'react';
import ScroolMenu from './ScroolMenu';
import { browserHistory } from 'react-router';
import {Grid,Row,Col,Button,Form,FormGroup,ControlLabel,FormControl} from 'react-bootstrap';

export default class DownPanel extends React.Component{
    constructor(props) {
        super(props);
        this.state = {writtenNumberOfPage: 0};
    }

    nextPage() {
        browserHistory.push(this.props.page+(Number(this.props.numberOfPage)+1));
    }

    changePage(e){
        browserHistory.push(this.props.page+(Number(this.state.writtenNumberOfPage)));
    }

    writeNumberOfPage(e){
        this.setState({writtenNumberOfPage: e.target.value});
    }

    render(){
        return (
            <Row className="downPanel">
                <Col sm={0} md={1} />
                <Col sm={12} md={10} >
                    <div className="buttonTollbar">
                        <Button bsStyle="primary" onClick={this.nextPage.bind(this)} className="NextPage">Next page!</Button><Button bsStyle="primary" className="Random"></Button>
                    <ScroolMenu page={this.props.page} numberOfPage={this.props.numberOfPage}/>
                        <Form inline id="PagePanel">
                            <FormGroup controlId="formInlinePage">
                                <ControlLabel>Page:</ControlLabel>
                                <FormControl type="text" onChange={this.writeNumberOfPage.bind(this)}/>
                            <Button onClick={this.changePage.bind(this)}>
                                Go!
                            </Button>
                            </FormGroup>
                        </Form>
                    </div>
                </Col>
                <Col sm={0} md={1} />
            </Row>
        );
    }
};
