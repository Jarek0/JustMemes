import React from 'react';
import Mem from './Mem';
import DownPanel from './DownPanel';
import {Grid,Row,Col} from 'react-bootstrap';


export default class MainPage extends React.Component{
    constructor(props){
        super(props);
        this.state = {memes: [],numberOfPage: 0};


    }

    componentDidMount(){

        this.showInitialPage(0);
    }


    showMemesFromPage(page){
        $.ajax({
            url: '/mem/page/'+page,
            type: 'GET'
        }).then(function(data) {
            this.setState({numberOfPage:page});
            this.setState({ memes: data.content });
        }.bind(this));
        window.scrollTo(0,0);
    }
    showInitialPage(page){
        $.ajax({
            url: '/mem/initialPage',
            type: 'GET'
        }).then(function(data) {
            this.setState({numberOfPage:page});
            this.setState({ memes: data.content });
        }.bind(this));
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.params.page !== this.props.params.page || nextProps.params.page !== undefined) {
            this.props.params.page=nextProps.params.page;
            this.showMemesFromPage(this.props.params.page);
        }
    }
    render(){

        return(
        <div>
        {
            this.state.memes.map((mem) =>
                <Mem title={mem.title} key={'mem-'+mem.id} id={mem.id} fileType={mem.fileType} />
            )

        }
        <DownPanel numberOfPage={this.state.numberOfPage}/>
        </div>
    );
    }
}
