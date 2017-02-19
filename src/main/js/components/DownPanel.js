import React from 'react';
import ScroolMenu from './ScroolMenu';
import {Grid,Row,Col,Button,Form,FormGroup,ControlLabel,FormControl} from 'react-bootstrap';

export default class DownPanel extends React.Component{
    constructor(props) {
        super(props);
    }

    render(){
        return (
            <Row className="downPanel">
                <Col sm={0} md={1} />
                <Col sm={12} md={10} >
                    <div className="buttonTollbar">
                        <Button bsStyle="primary" className="NextPage">Next page!</Button><Button bsStyle="primary" className="Random"></Button>
                    <ScroolMenu countOfPages={this.props.countOfPages} showMemesFromPage={this.props.showMemesFromPage.bind(this)}/>
                        <Form inline id="PagePanel">
                            <FormGroup controlId="formInlinePage">
                                <ControlLabel>Page:</ControlLabel>
                                <FormControl type="text" />
                            <Button type="submit">
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
